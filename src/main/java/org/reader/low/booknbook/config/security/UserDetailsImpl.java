package org.reader.low.booknbook.config.security;

import lombok.AllArgsConstructor;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * The type User details.
 */
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    /**
     * The Usuario.
     */
    private final Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getNombreUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return usuario.getNombre();
    }

    /**
     * Gets rol.
     *
     * @return the rol
     */
    public String getRol() {
        return usuario.getRol();
    }
}
