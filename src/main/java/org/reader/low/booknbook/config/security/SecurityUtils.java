package org.reader.low.booknbook.config.security;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The type Security utils.
 */
@Slf4j
public class SecurityUtils {

    /**
     * Gets user details.
     *
     * @return the user details
     */
    public static UsernamePasswordAuthenticationToken getUserDetails() {
        if(!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        }
        return null;
    }


    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public static String getNombre() { return getUserDetails() != null ?  ((String)((Claims)getUserDetails().getCredentials()).get("name")): null; }

    /**
     * Gets username.
     *
     * @return the username
     */
    public static String getUsername() { return getUserDetails() != null ? (String)getUserDetails().getPrincipal() : null; }

    /**
     * Gets rol.
     *
     * @return the rol
     */
    public static String getRol() {
        return getUserDetails() != null ? ((String)((Claims)getUserDetails().getCredentials()).get("rol")) : null;
    }
}
