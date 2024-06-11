package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.config.security.TokenUtils;
import org.reader.low.booknbook.controller.response.usuario.CalendarioUsuarioResponse;
import org.reader.low.booknbook.controller.object.*;
import org.reader.low.booknbook.controller.request.usuario.RolRequest;
import org.reader.low.booknbook.controller.request.usuario.UpdatePerfilUsuario;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.PaginationInfo;
import org.reader.low.booknbook.controller.response.UsernameResponse;
import org.reader.low.booknbook.controller.response.usuario.*;
import org.reader.low.booknbook.mapper.ResponseMapping;
import org.reader.low.booknbook.model.bnb.*;
import org.reader.low.booknbook.model.bnb.id.IdSeguimiento;
import org.reader.low.booknbook.persistence.repository.GeneroRepository;
import org.reader.low.booknbook.persistence.repository.PredicatesCriteria;
import org.reader.low.booknbook.persistence.repository.SeguimientoRepository;
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
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import static org.reader.low.booknbook.utils.ApplicationUtils.filteringListPage;

/**
 * The type User service.
 */
@Slf4j
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    /**
     * The Usuario repository.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * The Seguimiento repository.
     */
    @Autowired
    private SeguimientoRepository seguimientoRepository;

    /**
     * The Predicates criteria.
     */
    @Autowired
    private PredicatesCriteria predicatesCriteria;

    /**
     * The Genero repository.
     */
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
                    .genero(usuarioGet.getPreferenciaUsuario() != null ? usuarioGet.getPreferenciaUsuario().stream().filter(genero -> "GENERO".equals(genero.getTipo()))
                            .findFirst().map(genero ->  Combo.builder().id(genero.getId()).nombre(genero.getNombre()).build()).orElse(null) : null)
                    .tipo(usuarioGet.getPreferenciaUsuario() != null ? usuarioGet.getPreferenciaUsuario().stream().filter(genero -> "TIPO".equals(genero.getTipo()))
                            .map(genero ->  Combo.builder().id(genero.getId()).nombre(genero.getNombre()).build()).findFirst().orElse(null) : null)
                    .imagenPerfil(usuarioGet.getFotoPerfil())
                    .nombre(usuarioGet.getNombre())
                    .apellidoUno(usuarioGet.getApellido1())
                    .apellidoDos(usuarioGet.getApellido2())
                    .isFollow(usuarioSeguido(usuarioGet))
                    .email(SecurityUtils.getUsername().equals(username) ? usuarioGet.getCorreo() : null)
                    .build();
        } else{
            throw new BadRequestHanderException("usuario_existe","El usuario no pertenece a nuestra comunidad");
        }

        usuarioPerfil.setSelfPerfil(SecurityUtils.getUsername().equals(username));
        return usuarioPerfil;
    }

    /**
     * Usuario seguido boolean.
     *
     * @param usuario the usuario
     * @return the boolean
     */
    boolean usuarioSeguido(Usuario usuario){
        boolean seguido = usuario.getSeguido().stream()
                .filter(seguimiento ->
                        seguimiento.getIdSeguido().getNombreUsuario()
                                .equals(SecurityUtils.getUsername()))
                .filter(Seguimiento::isSeguido)
                .toList().size()>0;
        boolean seguidor = usuario.getSeguidor().stream()
                .filter(seguimiento ->
                        seguimiento.getIdSeguidor().getNombreUsuario()
                                .equals(SecurityUtils.getUsername()))
                .filter(Seguimiento::isSeguidor)
                .toList().size()>0;
        return seguido || seguidor;
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
        if(StringUtils.hasText(request.getEmail())){
            usuario.setCorreo(request.getEmail());
        }
        if(StringUtils.hasText(request.getPassword())){
            usuario.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        }
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

    /**
     * Add genero.
     *
     * @param usuario    the usuario
     * @param tipoGenero the tipo genero
     * @param id         the id
     */
    void addGenero(Usuario usuario, String tipoGenero, Long id) {
        Genero tipoRemove = usuario.getPreferenciaUsuario() != null ? usuario.getPreferenciaUsuario().stream()
                .filter(genero -> tipoGenero.equals(genero.getTipo())).findFirst().orElse(null) : null;
        Genero tipoAdd = generoRepository.getReferenceById(id);
            usuario.removePreferenciaUsuario(tipoRemove);
            usuario.addPreferenciaUsuario(tipoAdd);
    }

    /**
     * Remove genero.
     *
     * @param usuario    the usuario
     * @param tipoGenero the tipo genero
     */
    static void removeGenero(Usuario usuario, String tipoGenero){
        Genero generoRemove = usuario.getPreferenciaUsuario() != null ? usuario.getPreferenciaUsuario().stream()
                .filter(genero -> tipoGenero.equals(genero.getTipo())).findFirst().orElse(null) : null;
        usuario.removePreferenciaUsuario(generoRemove);
    }

    /**
     * Update token.
     *
     * @param usuario the usuario
     */
    void updateToken(Usuario usuario){
        String token = TokenUtils.createToken(usuario.getNombreUsuario(), usuario.getNombre(), usuario.getRol());
        UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(usernamePAT);
    }

    public UsernameResponse deleteUsuario(String username, boolean self) {
            Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(self ? SecurityUtils.getUsername() : username);
            if(usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                usuario.setEstado(false);
                usuario = usuarioRepository.save(usuario);
                return UsernameResponse.builder().id(usuario.getNombreUsuario()).message("Se ha desabilitado el usuario").build();
            }
        return UsernameResponse.builder().id(null).message("No se ha podido desabilitar el usuario").build();
    }

    @Override
    public LibrosPropiosUsuarioResponse librosPropios(Integer pageIndex, Integer size, String estado) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername());
        if(usuario.isPresent()){
            List<Valoracion> valoraciones = usuario.get().getValoracion();
            List<LecturaUsuario> libros = valoraciones.stream().filter(valoracion -> estado.equals(valoracion.getEstado()))
                    .map(ResponseMapping::mapToLecturaUsuario).toList();
            Pageable page = PageRequest.of(pageIndex, size);
            Page<LecturaUsuario> gruposPage = new PageImpl<>(
                    filteringListPage(libros, page),
                    page, libros.size());
            return LibrosPropiosUsuarioResponse.builder().libros(gruposPage.getContent())
                    .pageInfo(ResponseMapping.mapToPaginationInfo(page, libros)).build();
        }
        return LibrosPropiosUsuarioResponse.builder().build();
    }

    @Override
    public IdResponse seguirUsuario(String username, boolean accion) {
        if(username.equals(SecurityUtils.getUsername())){
            throw new BadRequestHanderException("follow_self","No puede seguirse a si mismo");
        }
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(username);
        if(usuario.isPresent()){
            Usuario usuarioSelf = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
            Optional<Seguimiento> seguimiento = seguimientoRepository.findById(IdSeguimiento.builder()
                    .idSeguidor(usuarioSelf.getId())
                    .idSeguido(usuario.get().getId())
                    .build());
            Seguimiento seguimientoGet = seguimiento.orElseGet(()->seguimientoRepository.findById(IdSeguimiento.builder()
                            .idSeguidor(usuario.get().getId())
                            .idSeguido(usuarioSelf.getId())
                            .build()).orElse(Seguimiento.builder()
                            .id(IdSeguimiento.builder()
                                    .idSeguidor(usuarioSelf.getId())
                                    .idSeguido(usuario.get().getId())
                                    .build())
                            .seguidor(accion)
                            .seguido(!accion)
                            .idSeguidor(usuarioSelf)
                            .idSeguido(usuario.get())
                            .build()));

            if(Objects.equals(seguimientoGet.getIdSeguidor().getId(), usuarioSelf.getId())){
                seguimientoGet.setSeguidor(accion);
            }else if(Objects.equals(seguimientoGet.getIdSeguido().getId(), usuarioSelf.getId())){
                seguimientoGet.setSeguido(accion);
            }
            seguimientoRepository.save(seguimientoGet);
        }
        return IdResponse.builder().id(usuario.get().getId())
                .message((accion ? "Ha seguido ": "Ha dejado de seguir ")+ "al usuario '"+ usuario.get().getNombreUsuario()+"'").build();
    }

    @Override
    public ContadorUsuarioResponse estadistica() {
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        Map<Long, Long> map = usuario.getValoracion().stream().filter(valoracion -> "LEIDO".equals(valoracion.getEstado()))
                .filter(valoracion -> valoracion.getLibro().getGenero() != null)
                .map(valoracion -> valoracion.getLibro().getGenero())
                .collect(Collectors.groupingBy(Genero::getId, Collectors.mapping((Genero g) -> g, Collectors.counting())));
        Map.Entry<Long, Long> mapIdGenero = map.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
        if(mapIdGenero  != null){
            Long idGenero = mapIdGenero.getKey();
            if(idGenero != null && idGenero != 0){
                Optional<Genero> genero = generoRepository.findById(idGenero);
                ComboGenero generoCombo = ComboGenero.builder().build();
                if(genero.isPresent()){
                    Genero generoGet = genero.get();
                    generoCombo = ComboGenero.builder().id(generoGet.getId()).nombre(generoGet.getNombre()).build();
                }
                Integer paginas = usuario.getValoracion().stream()
                        .filter(valoracion -> valoracion.getPaginaActual() != null).mapToInt(Valoracion::getPaginaActual).sum();
                return ContadorUsuarioResponse.builder()
                        .librosLeidos(usuario.getValoracion().stream().filter(valoracion -> "LEIDO".equals(valoracion.getEstado())).toList().size())
                        .paginasLeidas(Long.valueOf(paginas))
                        .valoraciones(usuario.getValoracion().stream().filter(valoracion -> valoracion.getComentario() != null).toList().size())
                        .genero(generoCombo)
                        .build();
            }
        }
        return ContadorUsuarioResponse.builder()
                .librosLeidos(0)
                .paginasLeidas(0L)
                .valoraciones(0)
                .genero(ComboGenero.builder().build())
                .build();
    }

    @Override
    public EstadisticaGeneroResponse estadisticaGenero() {
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        Map<String, Long> result = usuario.getValoracion().stream().filter(valoracion -> valoracion.getLibro().getGenero() != null)
                .map(valoracion -> valoracion.getLibro().getGenero())
                .collect(Collectors.groupingBy(Genero::getNombre, Collectors.mapping(genero -> genero, Collectors.counting())));
        List<Estadistica> estadisticas = result.entrySet().stream().map(ResponseMapping::mapToEstadistica).toList();
        return EstadisticaGeneroResponse.builder().estadisticas(estadisticas).build();
    }

    @Override
    public EstadisticaGeneroResponse estadisticaEstadoLibro() {
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        List<Estadistica> estadisticas = usuario.getValoracion().stream().filter(valoracion -> "LEIDO".equals(valoracion.getEstado()) ||
                        "PROGRESO".equals(valoracion.getEstado()) ||
                        "FAVORITO".equals(valoracion.getEstado()))
                .collect(Collectors.groupingBy(Valoracion::getEstado, Collectors.mapping(valoracion -> valoracion, Collectors.counting())))
                .entrySet().stream()
                .map(ResponseMapping::mapToEstadistica).toList();
        return EstadisticaGeneroResponse.builder().estadisticas(estadisticas).build();
    }



    @Override
    public CalendarioUsuarioResponse estadisticaCalendario(Integer anyoSelected) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        Map<Date, Long> valor = usuario.getPaginasLibro().stream()
                .collect(Collectors.groupingBy(paginasLibro -> paginasLibro.getId().getFecha(),
                        Collectors.mapping((PaginasLibro g) -> g, Collectors.summingLong(PaginasLibro::getPaginasLeidas))));
        List<CalendarioLectura> finale = valor.entrySet().stream().map(UserServiceImpl::mapToCalendarioLectura).toList();
        Set<Integer> anyos = finale.stream().collect(Collectors.groupingBy(CalendarioLectura::getYear)).keySet();
        if(anyoSelected != null && anyoSelected != 0){
            finale = finale.stream().filter(calendarioLectura -> Objects.equals(calendarioLectura.getYear(), anyoSelected)).toList();
        }else {
            Optional<Integer> anyo = finale.stream().min(Comparator.comparing(CalendarioLectura::getYear)).map(CalendarioLectura::getYear);

            if(anyo.isPresent()){
                log.error(String.valueOf(anyo.get()));
                finale = finale.stream().filter(calendarioLectura -> Objects.equals(calendarioLectura.getYear(), anyo.get())).toList();
            }
        }
        Map<Integer, List<CalendarioLectura>> result = finale.stream().collect(Collectors.groupingBy(CalendarioLectura::getYear));
        return CalendarioUsuarioResponse.builder().anyos(anyos).estadisticaPorAnio(result).build();
    }

    /**
     * Map to calendario lectura calendario lectura.
     *
     * @param mapa the mapa
     * @return the calendario lectura
     */
    static CalendarioLectura mapToCalendarioLectura(Map.Entry<Date, Long> mapa){
        String[] date = mapa.getKey().toString().split("-");
        Integer year = Integer.valueOf(date[0]);
        Integer month = Integer.valueOf(date[1]);
        Integer day = Integer.valueOf(date[2]);
        return CalendarioLectura.builder().year(year).month(month).day(day).paginasLeidas(mapa.getValue()).build();
    }


}
