package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.controller.interfaz.UserController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {

    @Override
    public void crearAutorImagen(MultipartFile imagen) throws IOException {

    }
}
