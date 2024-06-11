package org.reader.low.booknbook.config.security;

import lombok.NoArgsConstructor;
import org.reader.low.booknbook.config.error.hander.UnauthorizedHandlerException;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.reader.low.booknbook.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The type User detail service.
 */
@Service
@NoArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    /**
     * The Usuario repository.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(()->new UnauthorizedHandlerException("usuario_validation",
                        "El usuario introducido no pertenece a la comunidad BookNBook"));
        return new UserDetailsImpl(usuario);
    }
}
