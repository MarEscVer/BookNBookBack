package org.reader.low.booknbook.mapper;

import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RepositoryMapping {

    public static Usuario mapToUsuarioRegister(RegisterRequest register) {
        return Usuario.builder()
                .nombreUsuario(register.getUsuario())
                .nombre(register.getNombre())
                .apellido1(register.getApellidoPrimero())
                .apellido2(register.getApellidoSegundo())
                .correo(register.getEmail())
                .password(new BCryptPasswordEncoder().encode(register.getPassword()))
                .rol("NORMAL")
                .build();
    }
}
