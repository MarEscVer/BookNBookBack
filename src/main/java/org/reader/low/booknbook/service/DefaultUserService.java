package org.reader.low.booknbook.service;

import org.reader.low.booknbook.config.security.AuthCredentials;
import org.reader.low.booknbook.config.security.TokenResult;
import org.reader.low.booknbook.controller.request.usuario.LoginRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.controller.response.ContadorResponse;
import org.reader.low.booknbook.controller.response.LoginResponse;

/**
 * The interface Default user service.
 */
public interface DefaultUserService {

    /**
     * Gets token result.
     *
     * @param requestCredentials the request credentials
     * @param gen                the gen
     * @return the token result
     */
    TokenResult getTokenResult(AuthCredentials requestCredentials, boolean gen);

    /**
     * Login login response.
     *
     * @param loginRequest the login request
     * @return the login response
     */
    LoginResponse login(LoginRequest loginRequest);

    /**
     * Register.
     *
     * @param registerRequest the register request
     */
    void register(RegisterRequest registerRequest);

    /**
     * Contador contador response.
     *
     * @return the contador response
     */
    ContadorResponse contador();

}
