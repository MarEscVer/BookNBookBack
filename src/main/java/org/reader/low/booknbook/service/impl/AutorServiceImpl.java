package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.autor.UpdateAutorRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilLibrosResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.reader.low.booknbook.mapper.ResponseMapping;
import org.reader.low.booknbook.model.bnb.Autor;
import org.reader.low.booknbook.persistence.repository.AutorRepository;
import org.reader.low.booknbook.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static org.reader.low.booknbook.mapper.RepositoryMapping.mapToAutor;

/**
 * The type Autor service.
 */
@Slf4j
@NoArgsConstructor
@Service
public class AutorServiceImpl implements AutorService {

    /**
     * The Autor repository.
     */
    @Autowired
    private AutorRepository autorRepository;

    @Override
    public AutorPerfilResponse getAutorPerfil(Long idAutor) throws SQLException, IOException {
        Optional<Autor> autor = autorRepository.findById(idAutor);
        if(autor.isPresent()){
            return ResponseMapping.mapToAutorPerfilResponse(autor.get());
        } else {
            throw new BadRequestHanderException("autor_perfil", "No existe el autor seleccionado");
        }
    }

    @Override
    public IdResponse crearAutor(CreateAutorRequest createAutorRequest) {
        Optional<Autor> autorRegistrated = autorRepository.findByPseudonimo(createAutorRequest.getPseudonimo());
            if(autorRegistrated.isEmpty()){
                Autor autor = mapToAutor(createAutorRequest);
                Autor autorResponse = autorRepository.save(autor);
                return IdResponse.builder().id(autorResponse.getId())
                        .message("El autor "+ autorResponse.getPseudonimo() + " ha sido registrado correctamente").build();
            }else {
                throw new BadRequestHanderException("autor_existe", "El autor que intenta registrar ya existe");
            }
    }

    @Override
    public AutorPerfilLibrosResponse getAutorPerfilLibros(Long idAutor) {
        Optional<Autor> autor = autorRepository.findById(idAutor);
        if(autor.isPresent()){
            return ResponseMapping.
                    mapToAutorPerfilLibrosResponse(ResponseMapping.mapToListLibroObject(autor.get()));
        }else{
            throw new BadRequestHanderException("autor_perfil", "No existe el autor seleccionado");
        }
    }

    @Override
    public IdResponse updateAutor(UpdateAutorRequest updateAutorRequest) {
        Optional<Autor> autor = autorRepository.findById(updateAutorRequest.getId());
        if(autor.isPresent()){
            Autor autorGet = autor.get();
            autorGet.setBiografia(updateAutorRequest.getBiografia());
            autorGet.setLocalidad(updateAutorRequest.getLocalidad());
            autorGet.setPseudonimo(updateAutorRequest.getPseudonimo());
            autorGet = autorRepository.save(autorGet);
            return IdResponse.builder().id(autorGet.getId()).message("Autor Actualizado Correctamente").build();
        }
        return IdResponse.builder().id(null).message("Error al Actualizar el Autor").build();
    }
}
