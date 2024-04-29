package org.reader.low.booknbook.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET =
            "c2RhamxramZkbGtqYWxuZHdtd25kd2t3cWhl";

    /**
     * 1 semana
     */
    private final static Long ACCESS_TOKEN_VALIDITY_MILISECONDS = Long.valueOf(604800000);

    public static String createToken(String username, String name, String rol) {
        Date expirationDte = new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_MILISECONDS);
        // Incluir valores que transporta el token
        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);
        extra.put("rol", rol);
        return Jwts.builder().setSubject(username).setExpiration(expirationDte)
                .addClaims(extra).signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        //log
        SecretKey secret = Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload();
        String username = claims.getSubject();
        String rol = (String)claims.get("rol");
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+rol);
        System.out.println("PRUEBA "+authority);
        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        updatedAuthorities.add(authority);
        return new UsernamePasswordAuthenticationToken(username, claims, updatedAuthorities);
    }
}
