package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.config.security.TokenUtils;
import org.reader.low.booknbook.controller.object.ValoracionUsuario;
import org.reader.low.booknbook.controller.request.usuario.RolRequest;
import org.reader.low.booknbook.controller.request.usuario.UpdatePerfilUsuario;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.PaginationInfo;
import org.reader.low.booknbook.controller.response.UsernameResponse;
import org.reader.low.booknbook.controller.response.usuario.PerfilUsuario;
import org.reader.low.booknbook.controller.response.usuario.UserInfoResponse;
import org.reader.low.booknbook.controller.response.usuario.ValoracionPerfilUsuarioResponse;
import org.reader.low.booknbook.mapper.ResponseMapping;
import org.reader.low.booknbook.model.bnb.Genero;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.reader.low.booknbook.persistence.repository.GeneroRepository;
import org.reader.low.booknbook.persistence.repository.PredicatesCriteria;
import org.reader.low.booknbook.persistence.repository.UsuarioRepository;
import org.reader.low.booknbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.reader.low.booknbook.utils.ApplicationUtils.filteringListPage;

@Slf4j
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PredicatesCriteria predicatesCriteria;

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public void setImagenUsuario(MultipartFile imagen) throws IOException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        usuario.setFotoPerfil(imagen.getBytes());
        usuarioRepository.save(usuario);
    }

    @Override
    public void updateRolUsuario(RolRequest request) {
        Optional<Usuario> optUsuario = usuarioRepository.findByNombreUsuario(request.getUsername());
        if(optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();
            if(!usuario.isEstado()) {
                throw new BadRequestHanderException("rol_usuario", "El usuario tiene la cuenta desactivada");
            }
            if(!usuario.getRol().equals(request.getRol())){
                usuario.setRol(request.getRol());
                usuarioRepository.save(usuario);
            }
        }else {
            throw new BadRequestHanderException("rol_usuario", "El usuario no se encuentra registrado");
        }
    }

    @Override
    public IdResponse updateEstadoUsuario(boolean estado) {
        String username = SecurityUtils.getUsername();
        Usuario user = usuarioRepository.findByNombreUsuario(username).get();
        user.setEstado(estado);
        return null;
    }

    @Override
    public UserInfoResponse getListUsuario(String username, Integer pageIndex, Integer size){
        List<Usuario> usuarios = predicatesCriteria.usuariosActivos(username, pageIndex, size);
        Pageable page = PageRequest.of(pageIndex, size);
        Page<Usuario> gruposPage = new PageImpl<>(
                filteringListPage(usuarios, page),
                page, usuarios.size());
        PaginationInfo pageInfo = ResponseMapping.mapToPaginationInfo(page, usuarios);
        return ResponseMapping.mapToUserInfoResponse(gruposPage.getContent(), pageInfo);
    }

    @Override
    public PerfilUsuario getPerfilUsuario(String username) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(username);
        PerfilUsuario usuarioPerfil = null;
        if (usuario.isPresent()) {
            Usuario usuarioGet = usuario.get();
            usuarioPerfil = PerfilUsuario.builder()
                    .username(username)
                    .genero(usuarioGet.getPreferenciaUsuario() != null ? usuarioGet.getPreferenciaUsuario().stream().filter(genero -> "GENERO".equals(genero.getTipo())).map(Genero::getNombre).findFirst().orElse(null) : null)
                    .tipo(usuarioGet.getPreferenciaUsuario() != null ? usuarioGet.getPreferenciaUsuario().stream().filter(genero -> "TIPO".equals(genero.getTipo())).map(Genero::getNombre).findFirst().orElse(null) : null)
                    .imagenPerfil(usuarioGet.getFotoPerfil())
                    .nombre(usuarioGet.getNombre())
                    .apellidoUno(usuarioGet.getApellido1())
                    .apellidoDos(usuarioGet.getApellido2())
                    .build();
        } else{
            throw new BadRequestHanderException("usuario_existe","El usuario no pertenece a nuestra comunidad");
        }

        usuarioPerfil.setSelfPerfil(SecurityUtils.getUsername().equals(username));
        return usuarioPerfil;
    }

    @Override
    public UsernameResponse updatePerfilUsuario(UpdatePerfilUsuario request) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        if(request.getIdTipo() != null && request.getIdGenero() != null &&
                request.getIdGenero() != 0 && request.getIdTipo() != 0 &&
                request.getIdTipo().equals(request.getIdGenero())){
            throw new BadRequestHanderException("genero_igual","El Genero y el Tipo no puede ser el mismo");
        }
        if(request.getIdGenero() != null && request.getIdGenero() != 0){
            addGenero(usuario, "GENERO", request.getIdGenero());
            usuario = usuarioRepository.save(usuario);
        }else if(request.getIdGenero() == null){
            removeGenero(usuario, "GENERO");
            usuario = usuarioRepository.save(usuario);
        }
        if(request.getIdTipo() != null && request.getIdTipo() != 0){
            addGenero(usuario, "TIPO", request.getIdTipo());
            usuario = usuarioRepository.save(usuario);
        }else if(request.getIdTipo() == null) {
            removeGenero(usuario, "TIPO");
            usuario = usuarioRepository.save(usuario);
        }
        usuario.setNombre(request.getNombre());
        usuario.setApellido1(request.getApellidoPrimero());
        usuario.setApellido2(request.getApellidoSegundo());
        usuario.setCorreo(request.getEmail());
        usuario.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        usuarioRepository.save(usuario);
        updateToken(usuario);
        return UsernameResponse.builder().id(usuario.getNombreUsuario()).message("Usuario Actualizado Correctamente").build();
    }

    @Override
    public ValoracionPerfilUsuarioResponse getListValoracionPerfil(String username){
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(username);
        if(usuario.isPresent()){
            Usuario usuarioGet = usuario.get();
            List<ValoracionUsuario> listaValoracion = usuarioGet.getValoracion().stream()
                    .filter(valoracion -> valoracion.getComentario() != null && StringUtils.hasText(valoracion.getComentario()))
                    .map(valoracion -> ValoracionUsuario.builder()
                            .username(usuarioGet.getNombreUsuario())
                            .fechaComentario(valoracion.getFechaComentario())
                            .comentario(valoracion.getComentario())
                            .valoracion(valoracion.getCalificacionPersonal())
                            .valoracionIdLibro(valoracion.getLibro().getId())
                            .valoracionIdUsuario(usuarioGet.getId())
                            .imagenUsuario(usuarioGet.getFotoPerfil())
                            .estaDenunciado(valoracion.getDenuncia()!=null)
                            .build()).toList();
            return ValoracionPerfilUsuarioResponse.builder().valoraciones(listaValoracion).build();
        }
        return ValoracionPerfilUsuarioResponse.builder().valoraciones(new ArrayList<>()).build();
    }

    private void addGenero(Usuario usuario, String tipoGenero, Long id) {
        Genero tipoRemove = usuario.getPreferenciaUsuario() != null ? usuario.getPreferenciaUsuario().stream()
                .filter(genero -> tipoGenero.equals(genero.getTipo())).findFirst().orElse(null) : null;
        Genero tipoAdd = generoRepository.getReferenceById(id);
            usuario.removePreferenciaUsuario(tipoRemove);
            usuario.addPreferenciaUsuario(tipoAdd);
    }
    private static void removeGenero(Usuario usuario, String tipoGenero){
        Genero generoRemove = usuario.getPreferenciaUsuario() != null ? usuario.getPreferenciaUsuario().stream()
                .filter(genero -> tipoGenero.equals(genero.getTipo())).findFirst().orElse(null) : null;
        usuario.removePreferenciaUsuario(generoRemove);
    }

    private void updateToken(Usuario usuario){
        String token = TokenUtils.createToken(usuario.getNombreUsuario(), usuario.getNombre(), usuario.getRol());
        UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(usernamePAT);
    }
}
