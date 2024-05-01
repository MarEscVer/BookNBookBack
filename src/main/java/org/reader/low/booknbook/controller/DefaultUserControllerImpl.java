package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.security.AuthCredentials;
import org.reader.low.booknbook.config.security.TokenResult;
import org.reader.low.booknbook.controller.interfaz.DefaultUserController;
import org.reader.low.booknbook.controller.request.usuario.LoginRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;
import org.reader.low.booknbook.controller.response.LoginResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.service.DefaultUserService;
import org.reader.low.booknbook.service.GrupoService;
import org.reader.low.booknbook.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    LibroService libroService;

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
    public ListaLibrosRecomendadosResponse listaLibrosRecomendados(Integer pageIndex, Integer size, String filter) {
        return libroService.getListRecomendados(pageIndex, size, filter);
    }

    /*@Override
    public ListaLibrosRecomendadosResponse listaGrupos(Integer pageIndex, Integer size, String filter) {

        Pageable pageable = PageRequest.of(pageIndex, size);
*//*
        Page<ModerateComments> moderateComments = new PageImpl<ModerateComments>(
                filteringListPage(denuncias, pageable),
                pageable, denuncias.size());*//*
        return null;
    }*/

    @Override
    public ListGrupoResponse getListGrupos(Integer pageIndex, Integer size, String filter) {
        if (!StringUtils.hasText(filter)){
            filter = "";
        }
        return grupoService.getListGroup(pageIndex, size, filter, false);
    }


}
