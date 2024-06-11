package org.reader.low.booknbook.config.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * The type Web security config.
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class WebSecurityConfig {

    /**
     * The User details service.
     */
    private final UserDetailsService userDetailsService;
    /**
     * The Jwt authorization filter.
     */
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    /**
     * Filter chain security filter chain.
     *
     * @param http        the http
     * @param authManager the auth manager
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        // devuelve un header en ese endpoint, no usar para /token
        jwtAuthenticationFilter.setFilterProcessesUrl("/tokenjwt");
            return http.cors().and().authorizeHttpRequests().requestMatchers("/api/**").authenticated()
                    .and().authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
                    .and().authorizeHttpRequests().anyRequest().permitAll().and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                    .csrf().disable().build();
    }

    /**
     * Cors configuration source cors configuration source.
     *
     * @return the cors configuration source
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Auth manager authentication manager.
     *
     * @param http the http
     * @return the authentication manager
     * @throws Exception the exception
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder())
                    .and().build();
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
