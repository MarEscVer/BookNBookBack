package org.reader.low.booknbook.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtils {

    private static UsernamePasswordAuthenticationToken getUserDetails() {
        return (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getNombre() {
        return getUserDetails().toString();
    }
}
