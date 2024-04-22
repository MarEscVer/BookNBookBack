package org.reader.low.booknbook.config.security;

import lombok.NoArgsConstructor;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.reader.low.booknbook.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = null;
        try {
            usuario = usuarioRepository.findByNombreUsuario(username).get(0);
        }catch(IndexOutOfBoundsException e){
            throw new UsernameNotFoundException("El usuario '" + username + "' no existe.");
        }
        return new UserDetailsImpl(usuario);
    }
}
