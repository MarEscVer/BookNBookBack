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
import org.reader.low.booknbook.controller.response.ContadorResponse;
import org.reader.low.booknbook.controller.response.LoginResponse;
import org.reader.low.booknbook.mapper.RepositoryMapping;
import org.reader.low.booknbook.model.bnb.Genero;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.reader.low.booknbook.persistence.repository.GeneroRepository;
import org.reader.low.booknbook.persistence.repository.GrupoRepository;
import org.reader.low.booknbook.persistence.repository.UsuarioRepository;
import org.reader.low.booknbook.persistence.repository.ValoracionRepository;
import org.reader.low.booknbook.service.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Default user service.
 */
@Slf4j
@NoArgsConstructor
@Service
public class DefaultUserServiceImpl implements DefaultUserService {

    /**
     * The Token service.
     */
    @Autowired
    private UserDetailsService tokenService;

    /**
     * The Usuario repository.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * The Grupo repository.
     */
    @Autowired
    private GrupoRepository grupoRepository;

    /**
     * The Valoracion repository.
     */
    @Autowired
    private ValoracionRepository valoracionRepository;

    /**
     * The Genero repository.
     */
    @Autowired
    private GeneroRepository generoRepository;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Usuario usuarioLogin = usuarioRepository.findByNombreUsuario(loginRequest.getUsername())
                .orElseThrow(()->new UnauthorizedHandlerException("usuario_validation",
                        "El usuario introducido no pertenece a la comunidad BookNBook"));
        if(!usuarioLogin.isEstado()){
            usuarioLogin.setEstado(true);
            usuarioRepository.save(usuarioLogin);
            throw new UnauthorizedHandlerException("usuario_reactivate", "Se ha vuelto a activar el usuario");
        }

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        if (bcrypt.matches(loginRequest.getPassword(), usuarioLogin.getPassword())) {
            return new LoginResponse(usuarioLogin.getNombreUsuario(),usuarioLogin.getRol(),
                    TokenUtils.createToken(usuarioLogin.getNombreUsuario(), usuarioLogin.getNombre(), usuarioLogin.getRol()));
        }else {
            throw new UnauthorizedHandlerException("login_password","La contraseña no es correcta. Vuelve a introducirla");
        }
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(registerRequest.getUsuario());
        if(usuario.isPresent()) {
            throw new BadRequestHanderException("register_usuarioExistente", "Usuario ya registrado.");
        }else{
            Usuario registro = RepositoryMapping.mapToUsuarioRegister(registerRequest);
            registro = usuarioRepository.save(registro);
            if(registerRequest.getIdGenero()  != null && registerRequest.getIdGenero() != 0){
                Genero genero = generoRepository.getReferenceById(registerRequest.getIdGenero());
                registro.addPreferenciaUsuario(genero);
            }
            if(registerRequest.getIdTipo()  != null && registerRequest.getIdTipo() != 0){
                Genero tipo = generoRepository.getReferenceById(registerRequest.getIdTipo());
                registro.addPreferenciaUsuario(tipo);
            }
            usuarioRepository.save(registro);
        }
    }

    @Override
    public TokenResult getTokenResult(AuthCredentials requestCredentials, boolean gen) {
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
    }

    @Override
    public ContadorResponse contador() {
        return ContadorResponse.builder()
                .lectoresTotales(usuarioRepository.count())
                .clubesCreados(grupoRepository.count())
                .librosLeidos(valoracionRepository.countByEstado("LEIDO"))
                .comentariosTotales(valoracionRepository.countByComentarioNotNull())
                .build();
    }
}
