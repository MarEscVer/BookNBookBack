package org.reader.low.booknbook.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;
import org.reader.low.booknbook.model.bnb.*;
import org.reader.low.booknbook.persistence.repository.*;
import org.reader.low.booknbook.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

import static org.reader.low.booknbook.mapper.RepositoryMapping.mapToLibro;
import static org.reader.low.booknbook.mapper.RepositoryMapping.mapToValoracion;
import static org.reader.low.booknbook.mapper.ResponseMapping.mapToListLibroDescripcion;

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
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        Libro libro = null;
        try {
            libro = libroRepository.getReferenceById(request.getIdLibro());
            Valoracion valoracion = mapToValoracion(request, usuario, libro);
            valoracionRepository.save(valoracion);
        }catch (EntityNotFoundException e) {
            throw new BadRequestHanderException("libro_no_encontrado","El libro que busca no se encuentra en nuestra biblioteca");
        }

    }

    @Override
    public void crearLibro(CreateLibroRequest request) {
        Autor autor = null;
        Genero genero = null;
        Genero tipo = null;
        Saga saga = null;
            try {
                autor = autorRepository.getReferenceById(request.getIdAutor());
                if(request.getGenero() != null){
                    genero = generoRepository.getReferenceById(request.getGenero());
                }
                if(request.getTipo() != null){
                    tipo = generoRepository.getReferenceById(request.getTipo());
                }
                if (StringUtils.hasText(request.getNuevaSaga())) {
                    saga = sagaRepository.save(Saga.builder().nombre(request.getNuevaSaga()).build());
                } else if(request.getSaga() != null && request.getSaga() != 0){
                    saga = sagaRepository.getReferenceById(request.getSaga());
                }

            } catch (RuntimeException e) {
                throw new BadRequestHanderException("crear_libro", "El autor que busca no se encuentra aun entre nuestros escritores");
            }
            Libro libro = mapToLibro(request, autor, genero, tipo, saga);
            libroRepository.save(libro);
    }

    @Override
    public ListaLibrosRecomendadosResponse getListRecomendados(Integer pageIndex, Integer size, String filter){
        List<Libro> leidos = predicatesCriteria.librosMasLeidos(pageIndex, size);
        List<Libro> recomendados = predicatesCriteria.librosRecomendados(filter, pageIndex, size);
        List<Libro> novedades = predicatesCriteria.librosNovedades(filter, pageIndex, size);
        List<Libro> otros = libroRepository.findAll();
        Collections.shuffle(otros);
        otros = otros.subList(0, 10);
        return ListaLibrosRecomendadosResponse.builder()
                .masLeidos(mapToListLibroDescripcion(leidos))
                .recomendados(mapToListLibroDescripcion(recomendados))
                .novedades(mapToListLibroDescripcion(novedades))
                .listaFiltro(mapToListLibroDescripcion(otros))
                .build();
    }




}
