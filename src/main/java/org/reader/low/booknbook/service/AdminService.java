package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    IdResponse createLibro(CreateLibroRequest createLibroRequest);
    IdResponse createAutor(CreateAutorRequest createAutorRequest);
    List<ModerateComments> listaComentariosDenunciados(Integer pageIndex, Integer size, String filter, String estado);
    void estadoDenuncia(Long idDenuncia, String estado);
    void setImagenLibro(Long idLibro, MultipartFile imagen) throws IOException;

    void setImagenAutor(Long idAutor, MultipartFile imagen) throws IOException;
}
