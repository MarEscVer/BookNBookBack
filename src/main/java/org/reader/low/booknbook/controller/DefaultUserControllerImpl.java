package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.security.AuthCredentials;
import org.reader.low.booknbook.config.security.TokenResult;
import org.reader.low.booknbook.controller.interfaz.DefaultUserController;
import org.reader.low.booknbook.controller.request.usuario.LoginRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.controller.response.ContadorResponse;
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;
import org.reader.low.booknbook.controller.response.LoginResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilLibrosResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.libro.LibroPerfil;
import org.reader.low.booknbook.service.AutorService;
import org.reader.low.booknbook.service.DefaultUserService;
import org.reader.low.booknbook.service.GrupoService;
import org.reader.low.booknbook.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping()
public class DefaultUserControllerImpl implements DefaultUserController {

    @Autowired
    private DefaultUserService userService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    @Override
    public TokenResult token(AuthCredentials requestCredentials, boolean gen) {
        return userService.getTokenResult(requestCredentials, gen);
    }

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        userService.register(registerRequest);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @Override
    public LibroPerfil getLibroPerfil(Long idLibro){
        return libroService.getLibroPerfil(idLibro);
    }

    @Override
    public ListaLibrosRecomendadosResponse listaLibrosRecomendados(Integer pageIndex, Integer size, String filter) {
         return libroService.getListRecomendados(pageIndex, size, filter);
    }

    @Override
    public ListaLibrosRecomendadosResponse listaLibrosNovedades(Integer pageIndex, Integer size, String genero) {
        return libroService.getListLibrosNovedades(pageIndex, size, genero);
    }

    @Override
    public ListaLibrosRecomendadosResponse listaLibrosLeidos(Integer pageIndex, Integer size, String genero) {
        return libroService.getListLibrosLeidos(pageIndex, size, genero);
    }

    @Override
    public ListaLibrosRecomendadosResponse listaLibrosAleatorios(Integer size, String genero) {
        return libroService.getListLibrosAleatorios(size, genero);
    }

    @Override
    public ListGrupoResponse getListGrupos(Integer pageIndex, Integer size, String filter) {
        if (!StringUtils.hasText(filter)){
            filter = "";
        }
        return grupoService.getListGroup(pageIndex, size, filter, false);
    }

    @Override
    public ContadorResponse contador() {
        return userService.contador();
    }

    @Override
    public AutorPerfilResponse getperfilAutor(Long idAutor) throws SQLException, IOException {
        return autorService.getAutorPerfil(idAutor);
    }

    @Override
    public AutorPerfilLibrosResponse getperfilAutorLibros(Long idAutor) throws SQLException, IOException {
        return autorService.getAutorPerfilLibros(idAutor);
    }
}
