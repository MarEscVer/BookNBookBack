package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.usuario.RolRequest;
import org.reader.low.booknbook.controller.request.usuario.UpdatePerfilUsuario;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.UsernameResponse;
import org.reader.low.booknbook.controller.response.usuario.LibrosPropiosUsuarioResponse;
import org.reader.low.booknbook.controller.response.usuario.PerfilUsuario;
import org.reader.low.booknbook.controller.response.usuario.UserInfoResponse;
import org.reader.low.booknbook.controller.response.usuario.ValoracionPerfilUsuarioResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    void setImagenUsuario(MultipartFile imagen) throws IOException;
    void updateRolUsuario(RolRequest request);
    IdResponse updateEstadoUsuario(boolean estado);
    UserInfoResponse getListUsuario(String username, Integer pageIndex, Integer size);
    PerfilUsuario getPerfilUsuario(String username);
    UsernameResponse updatePerfilUsuario(UpdatePerfilUsuario request);
    ValoracionPerfilUsuarioResponse getListValoracionPerfil(String username);

    UsernameResponse deleteUsuario(String username, boolean self);

    LibrosPropiosUsuarioResponse librosPropios(Integer pageIndex, Integer size,String estado);
}
