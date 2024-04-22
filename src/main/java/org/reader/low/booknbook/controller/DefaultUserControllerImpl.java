package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.security.AuthCredentials;
import org.reader.low.booknbook.config.security.TokenResult;
import org.reader.low.booknbook.controller.interfaz.DefaultUserController;
import org.reader.low.booknbook.controller.request.usuario.LoginRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.controller.response.LoginResponse;
import org.reader.low.booknbook.service.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
