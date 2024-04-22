package org.reader.low.booknbook.config.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.UnauthorizedHandlerException;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.reader.low.booknbook.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Primary
@Slf4j
public class JWTAuthorizationFilter extends GenericFilterBean {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Qualifier("handlerExceptionResolver")
    @Autowired
    private HandlerExceptionResolver resolver;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
            try {
                Usuario usuario = usuarioRepository.findByNombreUsuario((String)usernamePAT.getPrincipal()).get(0);

                validateRol(usernamePAT, usuario);
                SecurityContextHolder.getContext().setAuthentication(usernamePAT);
                //filterChain.doFilter(servletRequest, servletResponse);
            }catch (UnauthorizedHandlerException | IndexOutOfBoundsException error) {
                resolver.resolveException(httpServletRequest, httpServletResponse, null, error);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean validateRol(UsernamePasswordAuthenticationToken usernamePAT, Usuario usuario) throws UnauthorizedHandlerException{
        String rol = ((String)((Claims)usernamePAT.getCredentials()).get("rol"));
        if(!usuario.getRol().equals(rol)){
            throw new UnauthorizedHandlerException("token_validation",
                    "El token introducido no corresponde con el rol del usuario. " +
                            "Vuelve a iniciar Sesion");
        }
        return true;
    }
}
