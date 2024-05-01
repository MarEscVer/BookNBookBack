package org.reader.low.booknbook.service;

import org.reader.low.booknbook.config.security.AuthCredentials;
import org.reader.low.booknbook.config.security.TokenResult;
import org.reader.low.booknbook.controller.request.usuario.LoginRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.controller.response.LoginResponse;

public interface DefaultUserService {

    TokenResult getTokenResult(AuthCredentials requestCredentials, boolean gen);

    LoginResponse login(LoginRequest loginRequest);

    void register(RegisterRequest registerRequest);

}
