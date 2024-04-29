package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.interfaz.AdminController;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.response.denuncia.ModerateCommentsResponse;
import org.reader.low.booknbook.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {

    @Autowired
    private AdminService adminService;

    @Override
    public void crearLibro(CreateLibroRequest createLibroRequest) {
        adminService.createLibro(createLibroRequest);
    }

    @Override
    public void crearAutor(CreateAutorRequest createAutorRequest){ adminService.createAutor(createAutorRequest);}

    public ModerateCommentsResponse listaComentariosDenunciados(Integer pageIndex, Integer size, String filter){
        if(!StringUtils.hasText(filter)){
           filter = "";
        }
        return ModerateCommentsResponse.builder().comentariosDenunciados(
                adminService.listaComentariosDenunciados(pageIndex, size, filter)
        ).build();
    }
}
