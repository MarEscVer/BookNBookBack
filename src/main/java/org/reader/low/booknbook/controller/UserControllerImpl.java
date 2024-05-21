package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.interfaz.UserController;
import org.reader.low.booknbook.controller.request.usuario.UpdatePerfilUsuario;
import org.reader.low.booknbook.controller.response.UsernameResponse;
import org.reader.low.booknbook.controller.response.usuario.PerfilUsuario;
import org.reader.low.booknbook.controller.response.usuario.PerfilUsuarioLibrosFavoritosResponse;
import org.reader.low.booknbook.controller.response.usuario.ValoracionPerfilUsuarioResponse;
import org.reader.low.booknbook.service.LibroService;
import org.reader.low.booknbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

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
}
