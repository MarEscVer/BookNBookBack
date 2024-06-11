package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.controller.object.LibroDescripcion;
import org.reader.low.booknbook.controller.object.LibroFavorito;
import org.reader.low.booknbook.controller.object.ValoracionUsuario;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.request.libro.UpdateLibroRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;
import org.reader.low.booknbook.controller.response.libro.ComentarioPerfilLibroResponse;
import org.reader.low.booknbook.controller.response.libro.LibroPerfil;
import org.reader.low.booknbook.controller.response.libro.ListLibroGestionResponse;
import org.reader.low.booknbook.controller.response.usuario.PerfilUsuarioLibrosFavoritosResponse;
import org.reader.low.booknbook.controller.response.valoracion.ValoracionResponse;
import org.reader.low.booknbook.mapper.ResponseMapping;
import org.reader.low.booknbook.model.bnb.*;
import org.reader.low.booknbook.model.bnb.id.IdPaginasLibro;
import org.reader.low.booknbook.model.bnb.id.IdValoracion;
import org.reader.low.booknbook.persistence.repository.*;
import org.reader.low.booknbook.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.reader.low.booknbook.mapper.RepositoryMapping.*;
import static org.reader.low.booknbook.mapper.ResponseMapping.*;
import static org.reader.low.booknbook.utils.ApplicationUtils.filteringListPage;

/**
 * The type Libro service.
 */
@Slf4j
@NoArgsConstructor
@Service
public class LibroServiceImpl implements LibroService {

    /**
     * The Usuario repository.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * The Genero repository.
     */
    @Autowired
    private GeneroRepository generoRepository;

    /**
     * The Saga repository.
     */
    @Autowired
    private SagaRepository sagaRepository;

    /**
     * The Autor repository.
     */
    @Autowired
    private AutorRepository autorRepository;

    /**
     * The Libro repository.
     */
    @Autowired
    private LibroRepository libroRepository;

    /**
     * The Valoracion repository.
     */
    @Autowired
    private ValoracionRepository valoracionRepository;

    /**
     * The Paginas libro repository.
     */
    @Autowired
    private PaginasLibroRepository paginasLibroRepository;

    /**
     * The Predicates criteria.
     */
    @Autowired
    private PredicatesCriteria predicatesCriteria;

    @Override
    public void puntuarLibro(PuntuarLibroRequest request) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername());
        Optional<Libro> libro = libroRepository.findById(request.getIdLibro());
        if(libro.isPresent() && usuario.isPresent()) {
            Usuario usuarioGet = usuario.get();
            Libro libroGet = libro.get();
            Optional<Valoracion> valoracion = valoracionRepository.findById(IdValoracion.builder()
                    .idUsuario(usuarioGet.getId()).idLibro(libroGet.getId()).build());
            if(valoracion.isPresent()){
                Valoracion valoracionGet = valoracion.get();
                valoracionGet.setEstado("LEIDO");
                valoracionGet.setComentario(request.getComentario());
                valoracionGet.setCalificacionPersonal(request.getPuntuacion());
                valoracionGet.setFechaLectura(new Date(new java.util.Date().getTime()));
                valoracionRepository.save(valoracionGet);
            }else {
                throw new BadRequestHanderException("libro_no_encontrado","El libro no está en tu lista");
            }
        }else {
            throw new BadRequestHanderException("libro_no_encontrado","El libro que busca no se encuentra en nuestra biblioteca");
        }
    }

    @Override
    public IdResponse crearLibro(CreateLibroRequest request) {
        Optional<Autor> autor;
        Optional<Saga> saga = Optional.empty();
        Saga sagaGet = null;
        Libro libro = Libro.builder().build();
        libro = mapToLibroCreate(request, libro);
        if(request.getGenero() != null && request.getGenero() != 0){
            libro.setGenero(generoRepository.findById(request.getGenero()).orElse(null));
        }
        if(request.getTipo() != null && request.getTipo() != 0){
            libro.setTipo(generoRepository.findById(request.getTipo()).orElse(null));
        }
        if (StringUtils.hasText(request.getNuevaSaga())) {
            sagaGet = sagaRepository.save(Saga.builder().nombre(request.getNuevaSaga()).build());
        } else if(request.getSaga() != null && request.getSaga() != 0){
            saga = sagaRepository.findById(request.getSaga());
            if(saga.isEmpty()){
                throw new BadRequestHanderException("crear_libro","La saga seleccionada no está en nuestra librería");
            }
        }
        libro.setSaga(saga.orElse(sagaGet));
        autor = autorRepository.findById(request.getAutor());
        if(autor.isPresent()){
            Autor autorGet = autor.get();
            libro.setAutor(autorGet);
            libro = libroRepository.save(libro);
            Optional<Libro> libNew = autorGet.getLibros().stream().filter(lib -> request.getNombre().equals(lib.getNombre())).findFirst();

            return IdResponse.builder().id(libro.getId()).message("Libro creado correctamente").build();
        }else {
            throw new BadRequestHanderException("crear_libro","El autor seleccionado no está en nuestra agenda");
        }
    }

    @Override
    public ListaLibrosRecomendadosResponse getListRecomendados(Integer pageIndex, Integer size, String genero){
        List<Libro> recomendados = predicatesCriteria.librosRecomendados(genero, pageIndex, size)
                .stream().map(Valoracion::getLibro).toList();
        List<LibroDescripcion> list = mapToListLibroDescripcion(recomendados);
        Pageable pageable = PageRequest.of(pageIndex, size);
        return ListaLibrosRecomendadosResponse.builder()
                .filterName("Libros Recomendados")
                .libros(list)
                .pageInfo(ResponseMapping.mapToPaginationInfo(pageable, list))
                .build();
    }

    @Override
    public ListaLibrosRecomendadosResponse getListLibrosAleatorios(Integer size, String genero){
        List<Libro> otros = libroRepository.findAll();
        if(StringUtils.hasText(genero)){
            otros = otros.stream().filter(libro -> genero.equals(libro.getGenero().getNombre()) ||
                    genero.equals(libro.getTipo().getNombre())).toList();
        }
        Pageable pageable = PageRequest.of(0, size);
        Collections.shuffle(otros);
        otros = otros.subList(0, Math.min(otros.size(), size));
        List<LibroDescripcion> list = mapToListLibroDescripcion(otros);
        return ListaLibrosRecomendadosResponse.builder().filterName("Otros Libros")
                .libros(list)
                .pageInfo(ResponseMapping.mapToPaginationInfo(pageable, list))
                .build();
    }

    @Override
    public ListaLibrosRecomendadosResponse getListLibrosNovedades(Integer pageIndex, Integer size, String genero){
        List<Libro> novedades = predicatesCriteria.librosNovedades(genero, pageIndex, size);
        List<LibroDescripcion> list = mapToListLibroDescripcion(novedades);
        Pageable pageable = PageRequest.of(pageIndex, size);
        return ListaLibrosRecomendadosResponse.builder().filterName("Libros Actuales")
                .libros(list)
                .pageInfo(ResponseMapping.mapToPaginationInfo(pageable, list))
                .build();
    }

    @Override
    public ListaLibrosRecomendadosResponse getListLibrosLeidos(Integer pageIndex, Integer size, String genero){
        List<Libro> leidos = predicatesCriteria.librosMasLeidos(genero, pageIndex, size)
                .stream().map(Valoracion::getLibro).toList();
        List<LibroDescripcion> list = mapToListLibroDescripcion(leidos);
        Pageable pageable = PageRequest.of(pageIndex, size);
        return ListaLibrosRecomendadosResponse.builder().filterName("Libros más Leidos")
                .libros(list)
                .pageInfo(ResponseMapping.mapToPaginationInfo(pageable, list))
                .build();
    }

    @Override
    public ListLibroGestionResponse getListLibrosGestion(Integer pageIndex, Integer size, String filtro){
        List<Libro> libros = libroRepository.findAll();
        if(StringUtils.hasText(filtro)){
            libros = libros.stream().filter(getPredicateFilterLibro(filtro.toUpperCase())).toList();
        }
        Pageable pageable = PageRequest.of(pageIndex, size);
        Page<Libro> gruposPage = new PageImpl<>(
                filteringListPage(libros, pageable),
                pageable, libros.size());
        return ListLibroGestionResponse.builder()
                .libros(ResponseMapping.mapToListLibroGestionResponse(gruposPage.getContent()))
                .pageInfo(ResponseMapping.mapToPaginationInfo(pageable, libros))
                .build();
    }

    /**
     * Get predicate filter libro predicate.
     *
     * @param filtro the filtro
     * @return the predicate
     */
    static Predicate<Libro> getPredicateFilterLibro(String filtro){
        Predicate<Libro> sagaContainFiltro = libro -> libro.getSaga() != null &&
                libro.getSaga().getNombre().toUpperCase().contains(filtro);
        Predicate<Libro> tituloContainFiltro = libro -> libro.getNombre().toUpperCase().contains(filtro);
        Predicate<Libro> generoContainFiltro = libro -> libro.getGenero() !=null &&
                libro.getGenero().getNombre().toUpperCase().contains(filtro);
        Predicate<Libro> tipoContainFiltro = libro -> libro.getTipo() !=null &&
                libro.getTipo().getNombre().toUpperCase().contains(filtro);
        Predicate<Libro> autorContainFiltro = libro -> libro.getAutor() !=null &&
                libro.getAutor().getPseudonimo().toUpperCase().contains(filtro);
        return sagaContainFiltro.or(
                        tipoContainFiltro)
                .or(tituloContainFiltro)
                .or(generoContainFiltro)
                .or(autorContainFiltro);
    }

    @Override
    public IdResponse updateLibro(UpdateLibroRequest request){
        Optional<Libro> libro = libroRepository.findById(request.getId());
        if(libro.isPresent()){
            Libro libroGet = libro.get();
            mapToLibro(request, libroGet);
            if (request.getSaga() != null && request.getSaga() != 0) {
                Optional<Saga> saga = sagaRepository.findById(request.getSaga());
                libroGet.setSaga(saga.orElse(null));
            }else if(request.getSaga() == null){
                libroGet.setSaga(null);
            }
            String  mensaje = "Libro Actualizado Correctamente";
            if(request.getAutor() != null && request.getAutor() != 0) {
                Optional<Autor> autor = autorRepository.findById(request.getAutor());
                libroGet.setAutor(autor.orElse(null));
            }else if(request.getAutor() == null){
                mensaje += ". El autor no se ha actualizado porque no se ha señalado ninguno";
            }
            if(request.getGenero() != null && request.getGenero() != 0) {
                Optional<Genero> genero = generoRepository.findById(request.getGenero());
                libroGet.setGenero(genero.orElse(null));
            }else if(request.getGenero() == null){
                libroGet.setGenero(null);
            }
            if(request.getTipo() != null && request.getTipo() != 0) {
                Optional<Genero> tipo = generoRepository.findById(request.getTipo());
                libroGet.setTipo(tipo.orElse(null));
            }else if(request.getTipo() == null){
                libroGet.setTipo(null);
            }

            libroGet = libroRepository.save(libroGet);
            return IdResponse.builder().id(libroGet.getId()).message(mensaje).build();
        }
        throw new BadRequestHanderException("libro_existe","El libro que desea editar no está en nuestra biblioteca");

    }

    @Override
    public ValoracionResponse guardarLibroValoracion(Long idLibro, String estado) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        Optional<Valoracion> valoracion = valoracionRepository.findById(IdValoracion.builder()
                .idLibro(idLibro).idUsuario(usuario.getId()).build());
        if(valoracion.isPresent()) {
            return mapToValoracionResponse(valoracion.get());
        }else {
            Optional<Libro> libro = libroRepository.findById(idLibro);
            if(libro.isPresent()) {
                return mapToValoracionResponse(valoracionRepository
                        .save(mapToValoracion(libro.get(), usuario, estado)));
            }
        }
        throw new BadRequestHanderException("valoracion_error", "No tiene este libro entre sus lecturas");
    }

    @Override
    public ValoracionResponse actualizarLibroValoracion(ValoracionResponse request) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        Optional<Valoracion> valoracion = valoracionRepository.findById(IdValoracion.builder()
                .idLibro(request.getIdLibro()).idUsuario(usuario.getId()).build());
        if(valoracion.isPresent()) {
            Valoracion valoracionGet = valoracion.get();
            Integer pagActualPersist = valoracionGet.getPaginaActual();
            ResponseMapping.mapToValoracionUpdate(valoracionGet, request);
            valoracionGet = valoracionRepository.save(valoracionGet);
            Optional<PaginasLibro> pagLib = paginasLibroRepository.findById(IdPaginasLibro.builder()
                    .idLibro(valoracionGet.getLibro().getId())
                    .idUsuario(valoracionGet.getUsuario().getId())
                    .fecha(new Date(Instant.now().toEpochMilli()))
                    .build());
            PaginasLibro pagLibGet;
            if(pagLib.isPresent()){
                pagLibGet = pagLib.get();
                pagLibGet.setPaginasLeidas(pagLibGet.getPaginasLeidas()+(request.getPaginaActual() - (pagActualPersist != null ? pagActualPersist : 0)));
            }else {
                pagLibGet = PaginasLibro.builder()
                        .id(IdPaginasLibro.builder()
                                .idLibro(valoracionGet.getLibro().getId())
                                .idUsuario(valoracionGet.getUsuario().getId())
                                .fecha(new Date(Instant.now().toEpochMilli()))
                                .build())
                        .paginasLeidas(request.getPaginaActual() - (pagActualPersist != null ? pagActualPersist : 0))
                        .usuario(valoracionGet.getUsuario())
                        .libro(valoracionGet.getLibro())
                        .build();
            }
            paginasLibroRepository.save(pagLibGet);
            return mapToValoracionResponse(valoracionGet);
        }
        throw new BadRequestHanderException("valoracion_error", "No tiene este libro entre sus lecturas");
    }

    @Override
    public PerfilUsuarioLibrosFavoritosResponse getListLibrosFavoritosUsuario(Integer pageIndex, Integer size, String username, String filtro) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(username);
        Pageable pageable = PageRequest.of(pageIndex, size);
        if(usuario.isPresent()){
            Usuario usuarioGet = usuario.get();
            List<LibroFavorito> listaLibros = new ArrayList<>(usuarioGet.getValoracion().stream()
                    .filter(valoracion -> valoracion.getCalificacionPersonal() != null && valoracion.getCalificacionPersonal() != 0)
                    .map(valoracion -> LibroFavorito.builder()
                            .id(valoracion.getLibro().getId())
                            .nombre(valoracion.getLibro().getNombre())
                            .fechaPublicacion(valoracion.getLibro().getFechaPublicacion())
                            .fotoLibro(valoracion.getLibro().getFotoLibro())
                            .saga(valoracion.getLibro().getSaga() != null ? valoracion.getLibro().getSaga().getNombre() : null)
                            .paginasLibro(valoracion.getLibro().getPagTotal())
                            .valoracion(valoracion.getCalificacionPersonal())
                            .build())
                    .toList());
            Collections.sort(listaLibros, (o1, o2) -> o2.getValoracion() - o1.getValoracion());
            Page<LibroFavorito> gruposPage = new PageImpl<>(
                    filteringListPage(listaLibros, pageable),
                    pageable, listaLibros.size());
            return PerfilUsuarioLibrosFavoritosResponse.builder().libros(gruposPage.getContent())
                    .pageInfo(ResponseMapping.mapToPaginationInfo(pageable, listaLibros)).build();
        }
        return PerfilUsuarioLibrosFavoritosResponse.builder()
                .libros(new ArrayList<>()).pageInfo(ResponseMapping.mapToPaginationInfo(pageable, new ArrayList())).build();
    }

    @Override
    public LibroPerfil getLibroPerfil(Long idLibro) {
        Optional<Libro> libro = libroRepository.findById(idLibro);
        if(libro.isPresent()){
            return mapToLibroPerfil(libro.get());
        }
        return LibroPerfil.builder()
                .build();
    }

    @Override
    public ComentarioPerfilLibroResponse getComentariosLibro(Long idLibro, Integer pageIndex, Integer size) {
        Optional<Libro> libro = libroRepository.findById(idLibro);
        Pageable pageable = PageRequest.of(pageIndex, size);
        if(libro.isPresent()){

            Libro libroGet = libro.get();
            List<ValoracionUsuario> response = new ArrayList<>(libroGet.getValoracion().stream()
                    .filter(valoracion -> StringUtils.hasText(valoracion.getComentario()))
                    .map(valoracion ->
                            ValoracionUsuario.builder()
                                    .username(valoracion.getUsuario().getNombreUsuario())
                                    .fechaComentario(valoracion.getFechaComentario())
                                    .comentario(valoracion.getComentario())
                                    .valoracion(valoracion.getCalificacionPersonal())
                                    .valoracionIdLibro(valoracion.getLibro().getId())
                                    .valoracionIdUsuario(valoracion.getUsuario().getId())
                                    .imagenUsuario(valoracion.getUsuario().getFotoPerfil())
                                    .estaDenunciado(valoracion.getDenuncia() != null)
                                    .build()
                    ).toList());
            response.sort((val1, val2) -> val2.getFechaComentario().compareTo(val1.getFechaComentario()));
            Page<ValoracionUsuario> comentariosPage = new PageImpl<>(
                    filteringListPage(response, pageable),
                    pageable, response.size());
            return ComentarioPerfilLibroResponse.builder().valoraciones(comentariosPage.getContent())
                    .pageInfo(ResponseMapping.mapToPaginationInfo(pageable,response)).build();
        }
        return ComentarioPerfilLibroResponse.builder()
                .valoraciones(new ArrayList<>()).pageInfo(ResponseMapping.mapToPaginationInfo(pageable, new ArrayList())).build();
    }

    @Override
    public ListaLibrosRecomendadosResponse getListlibros(Integer pageIndex, Integer size, String genero, String filter) {
        String mensaje = "Libros";
        List<Libro> libros = libroRepository.findAll();
        if(StringUtils.hasText(genero)){
            libros = libros.stream().filter(libro -> genero.toUpperCase().equals(libro.getGenero() != null ? libro.getGenero().getNombre() : null) ||
                    genero.toUpperCase().equals(libro.getTipo() !=null ? libro.getTipo().getNombre() : null)).toList();
            mensaje += " del genero " + StringUtils.capitalize(genero);
        }
        if(StringUtils.hasText(filter)){
            libros = libros.stream().filter(libro -> libro.getNombre().toLowerCase().contains(filter.toLowerCase())).toList();
        }
        Pageable pageable = PageRequest.of(pageIndex, size);
        Page<Libro> librosPage = new PageImpl<>(
                filteringListPage(libros, pageable),
                pageable, libros.size());
        return ListaLibrosRecomendadosResponse.builder().filterName(mensaje)
                .libros(mapToListLibroDescripcion(librosPage.getContent()))
                .pageInfo(ResponseMapping.mapToPaginationInfo(pageable, libros))
                .build();
    }


}
