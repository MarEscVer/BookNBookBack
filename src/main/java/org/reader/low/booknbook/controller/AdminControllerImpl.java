package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.interfaz.AdminController;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.denuncia.ModerateCommentsResponse;
import org.reader.low.booknbook.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {

    @Autowired
    private AdminService adminService;

    @Override
    public IdResponse crearLibro(CreateLibroRequest createLibroRequest) {
        return adminService.createLibro(createLibroRequest);
    }

    @Override
    public void crearLibroImagen(Long idLibro, MultipartFile imagen) throws IOException {
        adminService.setImagenLibro(idLibro, imagen);
    }

    @Override
    public IdResponse crearAutor(CreateAutorRequest createAutorRequest){ return adminService.createAutor(createAutorRequest);}

    @Override
    public void crearAutorImagen(Long idAutor, MultipartFile imagen) throws IOException {
        adminService.setImagenAutor(idAutor, imagen);
    }

    @Override
    public ModerateCommentsResponse listaComentariosDenunciados(Integer pageIndex, Integer size, String filter, String estado){
        if(!StringUtils.hasText(filter)){
           filter = "";
        }
        if(!StringUtils.hasText(estado)){
            estado = "PENDIENTE";
        }
        return ModerateCommentsResponse.builder().estado(estado).comentariosDenunciados(
                adminService.listaComentariosDenunciados(pageIndex, size, filter, estado)
        ).build();
    }

    @Override
    public void cambiarEstadoDenuncia(Long idDenuncia, String estado){
        adminService.estadoDenuncia(idDenuncia, StringUtils.delete(estado.toUpperCase()," "));
    }

}
