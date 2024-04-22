package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.request.autor.AutorPerfilRequest;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.reader.low.booknbook.persistence.repository.AutorRepository;
import org.reader.low.booknbook.service.AutorService;
import org.springframework.stereotype.Service;

@Slf4j
@NoArgsConstructor
@Service
public class AutorServiceImpl implements AutorService {

    private AutorRepository autorRepository;

    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }
    @Override
    public AutorPerfilResponse getAutorPerfil(AutorPerfilRequest request) {
        return AutorPerfilResponse.builder().build();
    }
}
