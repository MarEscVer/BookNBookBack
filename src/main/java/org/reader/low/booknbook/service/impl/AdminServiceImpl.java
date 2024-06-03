package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.autor.UpdateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.request.libro.UpdateLibroRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.denuncia.MessageValoracion;
import org.reader.low.booknbook.controller.response.libro.ListLibroGestionResponse;
import org.reader.low.booknbook.controller.response.usuario.UserInfoResponse;
import org.reader.low.booknbook.model.bnb.Autor;
import org.reader.low.booknbook.model.bnb.Libro;
import org.reader.low.booknbook.model.bnb.Valoracion;
import org.reader.low.booknbook.model.bnb.id.IdValoracion;
import org.reader.low.booknbook.persistence.repository.AutorRepository;
import org.reader.low.booknbook.persistence.repository.LibroRepository;
import org.reader.low.booknbook.persistence.repository.ValoracionRepository;
import org.reader.low.booknbook.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private DenunciaService denunciaService;

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private UserService userService;


    @Override
    public IdResponse createLibro(CreateLibroRequest createLibroRequest) {
        return libroService.crearLibro(createLibroRequest);
        //return MessageResponse.builder().message("Libro creado Correctamente").build();
    }

    @Override
    public IdResponse createAutor(CreateAutorRequest createAutorRequest) {
        return autorService.crearAutor(createAutorRequest);
    }

    @Override
    public List<ModerateComments> listaComentariosDenunciados(Integer pageIndex, Integer size, String filter, String estado){
        return denunciaService.listaComentariosDenunciados(pageIndex, size, filter, estado);
    }

    @Override
    public void estadoDenuncia(Long idDenuncia, String estado) {
        denunciaService.estadoDenuncia(idDenuncia, estado);
    }

    @Override
    public void setImagenLibro(Long idLibro, MultipartFile imagen) throws IOException {
        Optional<Libro> libro = libroRepository.findById(idLibro);
        if(libro.isPresent()){
            Libro libroGet = libro.get();
            libroGet.setFotoLibro(imagen.getBytes());
            libroRepository.save(libroGet);
        }
    }

    @Override
    public void setImagenAutor(Long idAutor, MultipartFile imagen) throws IOException, SQLException {
        Optional<Autor> autor = autorRepository.findById(idAutor);
        if(autor.isPresent()){
            Autor autorGet = autor.get();
            autorGet.setFotoAutor(imagen.getBytes());
            autorRepository.save(autorGet);
        }
    }

    @Override
    public MessageValoracion valoracionMessage(Long idLibro, Long idUsuario) {
        Optional<Valoracion> optValoracion = valoracionRepository.findById(IdValoracion.builder().idUsuario(idUsuario).idLibro(idLibro).build());
        if(optValoracion.isPresent()){
            Valoracion valoracion = optValoracion.get();
            return MessageValoracion.builder().message(valoracion.getComentario()).build();
        }
        throw new BadRequestHanderException("valoracion_existe", "No se encuentra disponible esta valoración");
    }

    @Override
    public UserInfoResponse getUsuarioInfo(String username, Integer pageIndex, Integer size){
        return userService.getListUsuario(username, pageIndex, size);
    }

    @Override
    public ListLibroGestionResponse getListLibros(Integer pageIndex, Integer size, String filtro) {
        return libroService.getListLibrosGestion(pageIndex, size, filtro);
    }

    @Override
    public IdResponse updateLibro(UpdateLibroRequest request){
        return libroService.updateLibro(request);
    }

    @Override
    public IdResponse updateAutor(UpdateAutorRequest updateAutorRequest) {
        return autorService.updateAutor(updateAutorRequest);
    }

    @Override
    public IdResponse deleteLibro(Long idLibro) {
        Optional<Libro> libro = libroRepository.findById(idLibro);
        if(libro.isPresent()){
            Libro libroGet = libro.get();
            libroGet.setEstado("INACTIVO");
            libroGet = libroRepository.save(libroGet);
            return IdResponse.builder().id(libroGet.getId()).message("Se ha eliminado el libro").build();
        }
        return IdResponse.builder().id(0L).message("No se ha podido eliminar el libro").build();
    }
}
