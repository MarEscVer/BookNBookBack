package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.response.usuario.CalendarioUsuarioResponse;
import org.reader.low.booknbook.controller.interfaz.UserController;
import org.reader.low.booknbook.controller.request.usuario.UpdatePerfilUsuario;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.UsernameResponse;
import org.reader.low.booknbook.controller.response.usuario.*;
import org.reader.low.booknbook.service.LibroService;
import org.reader.low.booknbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The type User controller.
 */
@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {

    /**
     * The User service.
     */
    @Autowired
    private UserService userService;

    /**
     * The Libro service.
     */
    @Autowired
    private LibroService libroService;

    @Override
    public void crearUsuarioImagen(MultipartFile imagen) throws IOException {
        userService.setImagenUsuario(imagen);
    }

    @Override
    public PerfilUsuario getPerfilUsuario(String username) {
        return userService.getPerfilUsuario(username);
    }

    @Override
    public UsernameResponse updatePerfilUsuario(UpdatePerfilUsuario request){
        return userService.updatePerfilUsuario(request);
    }

    @Override
    public PerfilUsuarioLibrosFavoritosResponse getListLibrosFavoritosUsuario(Integer pageIndex, Integer size, String username, String filter) {
        return libroService.getListLibrosFavoritosUsuario(pageIndex, size, username, filter);
    }

    @Override
    public ValoracionPerfilUsuarioResponse getListValoracionPerfil(String username) {
        return userService.getListValoracionPerfil(username);
    }

    @Override
    public UsernameResponse deleteUsuario(){
        return userService.deleteUsuario(null, true);
    }

    @Override
    public LibrosPropiosUsuarioResponse librosPropios(Integer pageIndex, Integer size, String estado){
        return userService.librosPropios(pageIndex, size, estado);
    }

    @Override
    public IdResponse seguirUsuario(String username){
        return userService.seguirUsuario(username, true);
    }

    @Override
    public IdResponse noSeguirUsuario(String username){
        return userService.seguirUsuario(username, false);
    }

    @Override
    public ContadorUsuarioResponse estadisticaUsuario() {
        return userService.estadistica();
    }

    @Override
    public CalendarioUsuarioResponse estadisticaCalendarioUsuario(Integer anyoSelected) {
        return userService.estadisticaCalendario(anyoSelected);
    }

    @Override
    public EstadisticaGeneroResponse estadisticaGeneroUsuario() {
        return userService.estadisticaGenero();
    }

    @Override
    public EstadisticaGeneroResponse estadisticaEstadoLibro() {
        return userService.estadisticaEstadoLibro();
    }
}
