package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.NotAuthorizatedHanderException;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.model.bnb.Autor;
import org.reader.low.booknbook.model.bnb.Libro;
import org.reader.low.booknbook.persistence.repository.AutorRepository;
import org.reader.low.booknbook.persistence.repository.LibroRepository;
import org.reader.low.booknbook.service.AdminService;
import org.reader.low.booknbook.service.AutorService;
import org.reader.low.booknbook.service.DenunciaService;
import org.reader.low.booknbook.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@NoArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private DenunciaService denunciaService;


    @Override
    public IdResponse createLibro(CreateLibroRequest createLibroRequest) {
        if(adminRoleActive()) {
            Libro libro = libroService.crearLibro(createLibroRequest);
            return IdResponse.builder().id(libro.getId()).message("Libro creado Correctamente").build();
        } else {
            throw new NotAuthorizatedHanderException("crear_libro_rol","El libro solo puede agregarse con un rol permitido");
        }
    }

    @Override
    public IdResponse createAutor(CreateAutorRequest createAutorRequest) {
        if(adminRoleActive()) {
            return autorService.crearAutor(createAutorRequest);
        } else {
            throw new NotAuthorizatedHanderException("crear_autor_rol","El autor solo puede agregarse con un rol permitido");
        }
    }

    @Override
    public List<ModerateComments> listaComentariosDenunciados(Integer pageIndex, Integer size, String filter, String estado){
        return denunciaService.listaComentariosDenunciados(pageIndex, size, filter, estado);
    }

    @Override
    public void estadoDenuncia(Long idDenuncia, String estado) {
        denunciaService.estadoDenuncia(idDenuncia, estado);
    }

    @Override
    public void setImagenLibro(Long idLibro, MultipartFile imagen) throws IOException {
        Libro libro = libroRepository.getReferenceById(idLibro);
        libro.setFotoLibro(imagen.getBytes());
        libroRepository.save(libro);
    }

    @Override
    public void setImagenAutor(Long idAutor, MultipartFile imagen) throws IOException {
        Autor autor = autorRepository.getReferenceById(idAutor);
        autor.setFotoAutor(imagen.getBytes());
        autorRepository.save(autor);
    }


    private static boolean adminRoleActive(){
        return !"NORMAL".equals(SecurityUtils.getRol());
    }
}
