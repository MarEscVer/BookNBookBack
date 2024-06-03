package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.autor.UpdateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.UpdateLibroRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.denuncia.MessageValoracion;
import org.reader.low.booknbook.controller.response.libro.ListLibroGestionResponse;
import org.reader.low.booknbook.controller.response.usuario.UserInfoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    IdResponse createLibro(CreateLibroRequest createLibroRequest);
    IdResponse createAutor(CreateAutorRequest createAutorRequest);
    List<ModerateComments> listaComentariosDenunciados(Integer pageIndex, Integer size, String filter, String estado);
    void estadoDenuncia(Long idDenuncia, String estado);
    void setImagenLibro(Long idLibro, MultipartFile imagen) throws IOException;
    void setImagenAutor(Long idAutor, MultipartFile imagen) throws IOException, SQLException;
    MessageValoracion valoracionMessage(Long idLibro, Long idUsuario);
    UserInfoResponse getUsuarioInfo(String username, Integer pageIndex, Integer size);
    ListLibroGestionResponse getListLibros(Integer pageIndex, Integer size, String filtro);
    IdResponse updateLibro(UpdateLibroRequest request);
    IdResponse updateAutor(UpdateAutorRequest updateAutorRequest);
    IdResponse deleteLibro(Long idLibro);
}
