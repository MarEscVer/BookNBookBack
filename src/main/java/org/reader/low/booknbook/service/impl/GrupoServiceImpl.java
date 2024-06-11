package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.config.error.hander.UnauthorizedHandlerException;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.constants.Constants;
import org.reader.low.booknbook.controller.request.grupo.CreateGroupRequest;
import org.reader.low.booknbook.controller.response.DeleteResponse;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.PaginationInfo;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.grupo.ListNameGrupoResponse;
import org.reader.low.booknbook.mapper.ResponseMapping;
import org.reader.low.booknbook.model.bnb.Genero;
import org.reader.low.booknbook.model.bnb.Grupo;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.reader.low.booknbook.model.bnb.UsuarioGrupo;
import org.reader.low.booknbook.model.bnb.id.IdUsuarioGrupo;
import org.reader.low.booknbook.persistence.repository.*;
import org.reader.low.booknbook.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.reader.low.booknbook.mapper.RepositoryMapping.mapToGroup;
import static org.reader.low.booknbook.mapper.RepositoryMapping.mapToUsuarioGrupo;
import static org.reader.low.booknbook.mapper.ResponseMapping.*;
import static org.reader.low.booknbook.utils.ApplicationUtils.filteringListPage;

/**
 * The type Grupo service.
 */
@Slf4j
@NoArgsConstructor
@Service
public class GrupoServiceImpl implements GrupoService {

    /**
     * The Grupo repository.
     */
    @Autowired
    GrupoRepository grupoRepository;

    /**
     * The Usuario repository.
     */
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * The Usuario grupo repository.
     */
    @Autowired
    UsuarioGrupoRepository usuarioGrupoRepository;

    /**
     * The Genero repository.
     */
    @Autowired
    GeneroRepository generoRepository;

    /**
     * The Predicates criteria.
     */
    @Autowired
    PredicatesCriteria predicatesCriteria;


    @Override
    public IdResponse createGroup(CreateGroupRequest createGroupRequest) throws IOException {
        Optional<Genero> tipo = Optional.empty();
        Optional<Genero> genero = Optional.empty();
        Grupo grupo = mapToGroup(createGroupRequest);
        grupo = grupoRepository.save(grupo);
        if(createGroupRequest.getGenero() != null && createGroupRequest.getGenero() != 0){
            genero = generoRepository.findById(createGroupRequest.getGenero());
            grupo.setGenero(genero.orElse(null));
        }
        if(createGroupRequest.getGenero() != null && createGroupRequest.getGenero() != 0){
           tipo = generoRepository.findById(createGroupRequest.getTipo());
           grupo.setTipo(tipo.orElse(null));
        }
        grupo = grupoRepository.save(grupo);

        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();

        usuarioGrupoRepository.save(mapToUsuarioGrupo(grupo, usuario, "SIR", "ACTIVO"));
        return mapToIdResponseGrupo(grupo);
    }

    @Override
    public void setImageToGroup(Long idGrupo, MultipartFile imagen) throws IOException {
        Optional<Grupo> grupo = grupoRepository.findById(idGrupo);
        if(grupo.isPresent()) {
            Grupo grupoGet = grupo.get();
            grupoGet.setImagen(imagen.getBytes());
            grupoRepository.save(grupoGet);
        }else {
            throw new BadRequestHanderException("grupo_existe","El grupo que busca no existe");
        }

    }

    @Override
    public IdResponse pertenecer(Long idGrupo) {
        Optional<Grupo> grupo = grupoRepository.findById(idGrupo);
        Grupo grupoGet = grupo.orElseThrow(()-> new BadRequestHanderException("grupo_pertenecer", "No se encuentra el grupo seleccionado"));
        Usuario usuarioGet = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        Optional<UsuarioGrupo> usuarioGrupo = usuarioGrupoRepository.findById(new IdUsuarioGrupo(grupoGet.getId(), usuarioGet.getId()));
        UsuarioGrupo usuarioGrupoGet;
        if(usuarioGrupo.isPresent()){
            usuarioGrupoGet = usuarioGrupo.get();
            if("BANEADO".equals(usuarioGrupoGet.getRol())){
                throw new UnauthorizedHandlerException("grupo_baneado", "No puedes pertenecer a un grupo del que has sido baneado");
            }

            if("ABANDONADO".equals(usuarioGrupoGet.getRol())){
                usuarioGrupoGet.setRol("NORMAL");
                usuarioGrupoGet.setEstado("ACTIVO");
            }else {
                throw new BadRequestHanderException("grupo_pertenecer", "Ya eres miembro de este grupo");
            }
        }else{
            usuarioGrupoGet = mapToUsuarioGrupo(grupoGet, usuarioGet, "NORMAL", "ACTIVO");
        }
        UsuarioGrupo pertenecer = usuarioGrupoRepository.save(usuarioGrupoGet);
        return IdResponse.builder().id(idGrupo).message("Se ha unido al grupo '"+pertenecer.getGrupo().getNombre()+"'").build();
    }

    @Override
    public IdResponse abandonar(Long idGrupo) {
        Optional<Grupo> grupo = grupoRepository.findById(idGrupo);
        Grupo grupoGet = grupo.orElseThrow(()-> new BadRequestHanderException("grupo_abandonar", "No se encuentra el grupo seleccionado"));
        Usuario usuarioGet = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        UsuarioGrupo usuarioGrupo = usuarioGrupoRepository.findById(new IdUsuarioGrupo(grupoGet.getId(), usuarioGet.getId()))
                .orElseThrow(()-> new BadRequestHanderException("grupo_abandonar", "Aun no pertenece a este grupo"));
        if(usuarioGrupo.getRol().equals("SIR")){
            if(usuarioGrupo.getGrupo().getUsuarioGrupo().stream().filter(usuarioGrupo1 -> usuarioGrupo1.getRol().equals("SIR")).toList().size()>1){
                usuarioGrupo.setEstado("INACTIVO");
                usuarioGrupo.setRol("ABANDONADO");
            } else {
                throw new UnauthorizedHandlerException("grupo_abandonar","Tienes que poner a otro usuario como lider");
            }
        }else {
            usuarioGrupo.setEstado("INACTIVO");
            usuarioGrupo.setRol("ABANDONADO");
        }

        usuarioGrupo = usuarioGrupoRepository.save(usuarioGrupo);
        return IdResponse.builder().id(idGrupo).message("Ha salido del grupo '"+usuarioGrupo.getGrupo().getNombre()+"'").build();
    }

    @Override
    public ListGrupoResponse getListGroup(Integer pageIndex, Integer size, String filter, boolean needToken) {
        Pageable page = PageRequest.of(pageIndex, size);
        List<Grupo> grupos = predicatesCriteria.searchGroup(filter);
        grupos = grupos.stream().filter(grupo -> "ACTIVO".equals(grupo.getEstado())).toList();
        Page<Grupo> gruposPage = new PageImpl<>(
                filteringListPage(grupos, page),
                page, grupos.size());
        PaginationInfo pageInfo = ResponseMapping.mapToPaginationInfo(page, grupos);
        return mapToListGroupResponse(gruposPage.toList(), pageInfo, needToken);
    }

    @Override
    public ListNameGrupoResponse getListGrupoPropios(String type, Integer pageIndex, Integer size, String filter) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        List<UsuarioGrupo> userGroupList = usuarioGrupoRepository.findAllByUsuario(usuario);
        Pageable pageable = PageRequest.of(pageIndex, size);
        //grupos que administra
        if(Constants.A.equals(type)) {
            userGroupList = userGroupList.stream()
                    .filter(simple -> !"NORMAL".equals(simple.getRol())).toList();
        }else if(Constants.P.equals(type)){
            userGroupList = userGroupList.stream().filter(usuarioGrupo -> "ACTIVO".equals(usuarioGrupo.getGrupo().getEstado())).toList();
        }
        //usar el filtro por id o descripcion o nombre
        userGroupList = userGroupList.stream()
                .filter(usuarioGrupo -> "ACTIVO".equals(usuarioGrupo.getEstado()))
                .filter(simple ->
                simple.getGrupo().getId().toString().contains(filter) ||
                        simple.getGrupo().getDescripcion().contains(filter) ||
                        simple.getGrupo().getNombre().contains(filter)).toList();
        Page<UsuarioGrupo> usuarioGrupos = new PageImpl<>(
                filteringListPage(userGroupList, pageable),
                pageable, userGroupList.size());
        return mapToListNameGroupResponse(usuarioGrupos.getContent(), type, userGroupList, pageable);
    }

    @Override
    public DeleteResponse deleteGroup(Long idGrupo) {
            Optional<Grupo> grupo = grupoRepository.findById(idGrupo);
            if(grupo.isPresent()) {
                Grupo grupoGet = grupo.get();
                grupoGet.setEstado("INACTIVO");
                grupoGet = grupoRepository.save(grupoGet);
            return DeleteResponse.builder().message("Se ha eliminado el grupo '"+grupoGet.getNombre()+"'").build();
        } else {
            throw new BadRequestHanderException("delete_group", "Error al eliminar el grupo. No existe");
        }
    }
}
