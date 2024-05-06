package org.reader.low.booknbook.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.controller.object.Combo;
import org.reader.low.booknbook.controller.response.ComboResponse;
import org.reader.low.booknbook.controller.response.GeneroComboResponse;
import org.reader.low.booknbook.model.bnb.Autor;
import org.reader.low.booknbook.model.bnb.Genero;
import org.reader.low.booknbook.persistence.repository.*;
import org.reader.low.booknbook.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@NoArgsConstructor
@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private PredicatesCriteria predicatesCriteria;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private DenunciaRepository denunciaRepository;


    @Override
    public ComboResponse comboSagas(Long idAutor) {
        try {
            Autor autor = autorRepository.getReferenceById(idAutor);
            List<Combo> combo = autor.getLibro().stream()
                    .map(libro -> (Combo)Combo.builder().id(libro.getSaga().getId())
                            .nombre(libro.getSaga().getNombre()).build())
                    .distinct()
                    .toList();
            return ComboResponse.builder().valores(combo).build();
        }catch (EntityNotFoundException e){
            throw new BadRequestHanderException("combo_saga", "El autor que busca no se encuentra aun entre nuestros escritores");
        }
    }

    @Override
    public ComboResponse comboAutores(String filter){
        List<Autor> autorList = predicatesCriteria.searchAutor(filter);
        List<Combo> comboList = autorList.stream().map(autor -> (Combo)Combo.builder().id(autor.getId()).nombre(autor.getPseudonimo()).build()).toList();
        return ComboResponse.builder().valores(comboList).build();
    }

    @Override
    public GeneroComboResponse comboGenero() {
        List<Genero> genero = generoRepository.findAllByTipo("GENERO");
        List<Genero> tipo = generoRepository.findAllByTipo("TIPO");
        List<Combo> comboGenero = genero.stream()
                .map(gen -> (Combo)Combo.builder().id(gen.getId()).nombre(gen.getNombre()).build()).toList();
        List<Combo> comboTipo = tipo.stream()
                .map(gen -> (Combo)Combo.builder().id(gen.getId()).nombre(gen.getNombre()).build()).toList();
        return GeneroComboResponse.builder()
                .genero(ComboResponse.builder().valores(comboGenero).build())
                .tipo(ComboResponse.builder().valores(comboTipo).build())
                .build();
    }

    @Override
    public ComboResponse comboEstadoDenuncia(boolean comboComentarioGrupo) {
        List<Combo> comboList = denunciaRepository.findAllEstadosDistinct().stream()
                .filter(estado -> comboComentarioGrupo == estado.contains("GRUPO_"))
                .map(estado -> (Combo)Combo.builder().nombre(estado).build()).toList();
        return ComboResponse.builder().valores(comboList).build();
    }

    @Override
    public ComboResponse comboMotivoDenuncia() {
        Combo combo1 = Combo.builder().nombre("Odio").build();
        Combo combo2 = Combo.builder().nombre("Violencia").build();
        Combo combo3 = Combo.builder().nombre("Contenido Sexual").build();
        Combo combo4 = Combo.builder().nombre("Informacion Privada").build();
        Combo combo5 = Combo.builder().nombre("Otros").build();
        List<Combo> comboList = new ArrayList<>((Arrays.asList(combo1,combo2,combo3,combo4,combo5)));
        return ComboResponse.builder().valores(comboList).build();
    }

}
