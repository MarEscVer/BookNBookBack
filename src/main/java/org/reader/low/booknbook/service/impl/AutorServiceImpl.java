package org.reader.low.booknbook.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.controller.object.LibroObject;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.reader.low.booknbook.mapper.ResponseMapping;
import org.reader.low.booknbook.model.bnb.Autor;
import org.reader.low.booknbook.persistence.repository.AutorRepository;
import org.reader.low.booknbook.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.reader.low.booknbook.mapper.RepositoryMapping.mapToAutor;

@Slf4j
@NoArgsConstructor
@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public AutorPerfilResponse getAutorPerfil(Long idAutor) {
        Autor autor = autorRepository.getReferenceById(idAutor);
        try {
            List<LibroObject> listaLibros = ResponseMapping.mapToListLibroObject(autor);
            return ResponseMapping.mapToAutorPerfilResponse(autor, listaLibros);
        }catch (EntityNotFoundException e){
            throw new BadRequestHanderException("autor_perfil", "No existe el autor seleccionado");
        }
    }

    @Override
    public void crearAutor(CreateAutorRequest createAutorRequest) {
        Optional<Autor> autorRegistrated = autorRepository.findByPseudonimo(createAutorRequest.getPseudonimo());
            if(autorRegistrated.isEmpty()){
                Autor autor = mapToAutor(createAutorRequest);
                autorRepository.save(autor);
            }else {
                throw new BadRequestHanderException("autor_existe", "El autor que intenta registrar ya existe");
            }
    }




}
