package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.controller.object.LibroDescripcion;
import org.reader.low.booknbook.controller.object.LibroFavorito;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.request.libro.UpdateLibroRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;
import org.reader.low.booknbook.controller.response.libro.ListLibroGestionResponse;
import org.reader.low.booknbook.controller.response.usuario.PerfilUsuarioLibrosFavoritosResponse;
import org.reader.low.booknbook.controller.response.valoracion.ValoracionResponse;
import org.reader.low.booknbook.mapper.ResponseMapping;
import org.reader.low.booknbook.model.bnb.*;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.reader.low.booknbook.mapper.RepositoryMapping.mapToLibro;
import static org.reader.low.booknbook.mapper.RepositoryMapping.mapToValoracion;
import static org.reader.low.booknbook.mapper.ResponseMapping.mapToListLibroDescripcion;
import static org.reader.low.booknbook.mapper.ResponseMapping.mapToValoracionResponse;
import static org.reader.low.booknbook.utils.ApplicationUtils.filteringListPage;

@Slf4j
@NoArgsConstructor
@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private SagaRepository sagaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ValoracionRepository valoracionRepository;

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
    public void crearLibro(CreateLibroRequest request) {
        Optional<Autor> autor;
        Optional<Genero> genero = Optional.empty();
        Optional<Genero> tipo = Optional.empty();
        Saga saga = null;
        Libro libro = Libro.builder().build();
        libro = mapToLibro(request, libro.toBuilder());
        if(request.getGenero() != null && request.getGenero() != 0){
            genero = generoRepository.findById(request.getGenero());
            libro.setGenero(genero.orElse(null));
        }
        if(request.getTipo() != null && request.getTipo() != 0){
            tipo = generoRepository.findById(request.getTipo());
            libro.setTipo(tipo.orElse(null));
        }
        if (StringUtils.hasText(request.getNuevaSaga())) {
            saga = sagaRepository.save(Saga.builder().nombre(request.getNuevaSaga()).build());
        } else if(request.getSaga() != null && request.getSaga() != 0){
            saga = sagaRepository.findById(request.getSaga()).get();
        }
        libro.setSaga(saga);
        autor = autorRepository.findById(request.getIdAutor());
        Libro finalLibro = libro;
        Autor autorGet = null;
        if(autor.isPresent()){
            autor.get().addLibro(finalLibro);
            autorGet = autorRepository.save(autor.get());
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

    private static Predicate<Libro> getPredicateFilterLibro(String filtro){
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
            if (request.getSaga() != null && request.getSaga() != 0) {
                Optional<Saga> saga = sagaRepository.findById(request.getSaga());
                libroGet.setSaga(saga.orElse(null));
            }else{
                libroGet.setSaga(null);
            }
            String  mensaje = "Libro Actualizado Correctamente";
            if(request.getAutor() != null && request.getAutor() != 0) {
                Optional<Autor> autor = autorRepository.findById(request.getAutor());
                libroGet.setAutor(autor.orElse(null));
            }else {
                mensaje += ". El autor no se ha actualizado porque no se ha señalado ninguno";
            }
            if(request.getGenero() != null && request.getGenero() != 0) {
                Optional<Genero> genero = generoRepository.findById(request.getGenero());
                libroGet.setGenero(genero.orElse(null));
            }else {
                libroGet.setGenero(null);
            }
            if(request.getTipo() != null && request.getTipo() != 0) {
                Optional<Genero> tipo = generoRepository.findById(request.getTipo());
                libroGet.setTipo(tipo.orElse(null));
            }else{
                libroGet.setTipo(null);
            }
            libroGet = mapToLibro(request, libroGet);
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
            ResponseMapping.mapToValoracionUpdate(valoracionGet, request);
            valoracionGet = valoracionRepository.save(valoracionGet);
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
}
