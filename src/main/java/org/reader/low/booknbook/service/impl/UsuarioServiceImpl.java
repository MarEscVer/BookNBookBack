package org.reader.low.booknbook.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.request.usuario.LoginRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.service.UserService;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UserService {

    @Override
    public boolean login(LoginRequest loginRequest) {
        return false;
    }

    @Override
    public boolean register(RegisterRequest registerRequest) {
        return false;
    }
}
