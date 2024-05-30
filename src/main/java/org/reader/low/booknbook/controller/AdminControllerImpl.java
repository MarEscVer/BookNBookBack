package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.interfaz.AdminController;
import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.autor.UpdateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.UpdateLibroRequest;
import org.reader.low.booknbook.controller.request.usuario.RolRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.denuncia.MessageValoracion;
import org.reader.low.booknbook.controller.response.denuncia.ModerateCommentsResponse;
import org.reader.low.booknbook.controller.response.libro.ListLibroGestionResponse;
import org.reader.low.booknbook.controller.response.usuario.UserInfoResponse;
import org.reader.low.booknbook.mapper.ResponseMapping;
import org.reader.low.booknbook.service.AdminService;
import org.reader.low.booknbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

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
    public IdResponse updateAutor(UpdateAutorRequest updateAutorRequest){ return adminService.updateAutor(updateAutorRequest);}

    @Override
    public void crearAutorImagen(Long idAutor, MultipartFile imagen) throws IOException, SQLException {
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
        Pageable pageable = PageRequest.of(pageIndex, size);
        List<ModerateComments> list = adminService.listaComentariosDenunciados(pageIndex, size, filter, estado);
        return ModerateCommentsResponse.builder().estado(estado).comentariosDenunciados(list)
                .pageInfo(ResponseMapping.mapToPaginationInfo(pageable, list))
                .build();
    }

    @Override
    public void cambiarEstadoDenuncia(Long idDenuncia, String estado){
        adminService.estadoDenuncia(idDenuncia, StringUtils.delete(estado.toUpperCase()," "));
    }

    @Override
    public void cambiarRolUsuario(RolRequest request){
        userService.updateRolUsuario(request);
    }

    @Override
    public UserInfoResponse getUsuarioInfo(String username, Integer pageIndex, Integer size){
        return adminService.getUsuarioInfo(username, pageIndex, size);
    }

    @Override
    public MessageValoracion valoracionMessage(Long idLibro, Long idUsuario) {
        return adminService.valoracionMessage(idLibro, idUsuario);
    }

    @Override
    public ListLibroGestionResponse getListLibros(Integer pageIndex, Integer size, String filtro) {
        return adminService.getListLibros(pageIndex, size, filtro);
    }

    @Override
    public IdResponse updateLibro(UpdateLibroRequest request) {
        return adminService.updateLibro(request);
    }

}
