package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.usuario.LoginRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;

public interface UserService {

    boolean login(LoginRequest loginRequest);

    boolean register(RegisterRequest registerRequest);
}
