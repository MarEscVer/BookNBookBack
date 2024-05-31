package org.reader.low.booknbook.config.security;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtils {

    public static UsernamePasswordAuthenticationToken getUserDetails() {
        log.error("Errr "+ SecurityContextHolder.getContext().getAuthentication().toString());
        if(!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        }
        return null;
    }


    public static String getNombre() { return getUserDetails() != null ?  ((String)((Claims)getUserDetails().getCredentials()).get("name")): null; }

    public static String getUsername() { return getUserDetails() != null ? (String)getUserDetails().getPrincipal() : null; }

    public static String getRol() {
        return getUserDetails() != null ? ((String)((Claims)getUserDetails().getCredentials()).get("rol")) : null;
    }
}
