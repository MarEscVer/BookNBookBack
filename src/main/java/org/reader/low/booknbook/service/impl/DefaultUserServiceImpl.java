package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.config.error.hander.UnauthorizedHandlerException;
import org.reader.low.booknbook.config.security.AuthCredentials;
import org.reader.low.booknbook.config.security.TokenResult;
import org.reader.low.booknbook.config.security.TokenUtils;
import org.reader.low.booknbook.config.security.UserDetailsImpl;
import org.reader.low.booknbook.controller.request.usuario.LoginRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.controller.response.LoginResponse;
import org.reader.low.booknbook.mapper.RepositoryMapping;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.reader.low.booknbook.persistence.repository.UsuarioRepository;
import org.reader.low.booknbook.service.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@NoArgsConstructor
@Service
public class DefaultUserServiceImpl implements DefaultUserService {

    @Autowired
    private UserDetailsService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Usuario usuarioLogin = usuarioRepository.findByNombreUsuario(loginRequest.getUsername()).get(0);
        if(usuarioLogin != null) {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            if (bcrypt.matches(loginRequest.getPassword(), usuarioLogin.getPassword())) {
                return new LoginResponse(usuarioLogin.getNombreUsuario(),usuarioLogin.getRol(),
                        TokenUtils.createToken(usuarioLogin.getNombreUsuario(), usuarioLogin.getNombre(), usuarioLogin.getRol()));
            }else {
                throw new UnauthorizedHandlerException("login_password","La contraseña no es correcta. Vuelve a introducirla");
            }
        } else {
            throw new UnauthorizedHandlerException("login_username","No tenemos el usuario registrado. Registrate");
        }
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        try {
            Usuario usuario = usuarioRepository.findByNombreUsuario(registerRequest.getUsuario()).get(0);
            throw new BadRequestHanderException("register_usuarioExistente", "Usuario ya registrado.");
        }catch (IndexOutOfBoundsException e) {
            Usuario registro = RepositoryMapping.mapToUsuarioRegister(registerRequest);
            usuarioRepository.save(registro);
        }
    }

    @Override
    public TokenResult getTokenResult(AuthCredentials requestCredentials, boolean gen) {
        try {
            if(gen){
                return new TokenResult(TokenUtils.createToken(requestCredentials.getUsername(), null, null),
                        new BCryptPasswordEncoder().encode(requestCredentials.getPassword()));
            }
            UserDetailsImpl userDetails = (UserDetailsImpl) tokenService.loadUserByUsername(requestCredentials.getUsername());

            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            if (bcrypt.matches(requestCredentials.getPassword(), userDetails.getPassword())){
                return new TokenResult(TokenUtils.createToken(requestCredentials.getUsername(), userDetails.getNombre(), userDetails.getRol()),
                        userDetails.getPassword());
            }else {
                throw new UnauthorizedHandlerException("generate_token", "La contraseña no pertenece al usuario '" +
                        requestCredentials.getUsername() + "'");
            }
        }catch (UsernameNotFoundException e){
            throw new UnauthorizedHandlerException("generate_token", e.getLocalizedMessage());
        }
    }
}
