package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.model.bnb.Usuario;
import org.reader.low.booknbook.persistence.repository.UsuarioRepository;
import org.reader.low.booknbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void setImagenUsuario(MultipartFile imagen) throws IOException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(SecurityUtils.getUsername()).get();
        usuario.setFotoPerfil(imagen.getBytes());
        usuarioRepository.save(usuario);
    }
}
