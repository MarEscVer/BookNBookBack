package org.reader.low.booknbook.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    void setImagenUsuario(MultipartFile imagen) throws IOException;
}
