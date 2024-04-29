package org.reader.low.booknbook.service.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.NotAuthorizatedHanderException;
import org.reader.low.booknbook.config.security.SecurityUtils;
import org.reader.low.booknbook.controller.object.ModerateComments;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.mapper.ResponseMapping;
import org.reader.low.booknbook.model.bnb.Valoracion;
import org.reader.low.booknbook.persistence.repository.AutorRepository;
import org.reader.low.booknbook.persistence.repository.ValoracionRepository;
import org.reader.low.booknbook.service.AdminService;
import org.reader.low.booknbook.service.AutorService;
import org.reader.low.booknbook.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.reader.low.booknbook.mapper.FiltersMapping.filtroModerateComments;
import static org.reader.low.booknbook.utils.ApplicationUtils.filteringListPage;

@Slf4j
@NoArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private ValoracionRepository valoracionRepository;


    @Override
    public void createLibro(CreateLibroRequest createLibroRequest) {
        if(adminRoleActive()) {
            libroService.crearLibro(createLibroRequest);
        } else {
            throw new NotAuthorizatedHanderException("crear_libro_rol","El libro solo puede agregarse con un rol permitido");
        }
    }

    @Override
    public void createAutor(CreateAutorRequest createAutorRequest) {
        if(adminRoleActive()) {
            autorService.crearAutor(createAutorRequest);
        } else {
            throw new NotAuthorizatedHanderException("crear_autor_rol","El autor solo puede agregarse con un rol permitido");
        }
    }

    @Override
    public List<ModerateComments> listaComentariosDenunciados(Integer pageIndex, Integer size, String filter){
        List<Valoracion> valoracionList = valoracionRepository.findByDenunciaNotNullOrderByFechaComentarioDesc();
        Pageable pageable = PageRequest.of(pageIndex, size);

        List<ModerateComments> denuncias = valoracionList.stream()
                .map(ResponseMapping::maptToModerateComments).filter(comentario ->
                filtroModerateComments(filter, comentario)).toList();
        Page<ModerateComments> moderateComments = new PageImpl<ModerateComments>(
                filteringListPage(denuncias, pageable),
                pageable, denuncias.size());
        return moderateComments.toList();
    }




    private static boolean adminRoleActive(){
        return !"NORMAL".equals(SecurityUtils.getRol());
    }
}
