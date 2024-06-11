package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.constants.Constants;
import org.reader.low.booknbook.controller.interfaz.GrupoController;
import org.reader.low.booknbook.controller.response.DeleteResponse;
import org.reader.low.booknbook.controller.request.grupo.CreateGroupRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.grupo.ListNameGrupoResponse;
import org.reader.low.booknbook.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The type Grupo controller.
 */
@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/grupo")
public class GrupoControllerImpl implements GrupoController {

    /**
     * The Grupo service.
     */
    @Autowired
    private GrupoService grupoService;

    @Override
    public void createGroupImagen(Long idGrupo, MultipartFile imagen) throws IOException {
        grupoService.setImageToGroup(idGrupo, imagen);
    }

    @Override
    public IdResponse createGroup(CreateGroupRequest createGroupRequest) throws IOException {
        return grupoService.createGroup(createGroupRequest);
    }

    @Override
    public ListGrupoResponse getListGrupos(Integer pageIndex, Integer size, String filter) {
        if (!StringUtils.hasText(filter)){
            filter = "";
        }
        return grupoService.getListGroup(pageIndex, size, filter, true);
    }

    @Override
    public ListNameGrupoResponse getListGroup(String type, Integer pageIndex, Integer size, String filter) {
        if (!StringUtils.hasText(filter)){
            filter = "";
        }
        if(Constants.P.equals(type) || Constants.A.equals(type)) {
            return grupoService.getListGrupoPropios(type, pageIndex, size, filter);
        } else {
            throw new BadRequestHanderException("lista_grupo_admin", "El tipo en la llamada no es correcto");
        }
    }

    @Override
    public DeleteResponse deleteMyGroup(Long idGrupo) {
        return grupoService.deleteGroup(idGrupo);
    }

    @Override
    public IdResponse pertenecerAbandonarGrupo(Long idGrupo, String accion) {
        if("P".equals(accion)) {
            return grupoService.pertenecer(idGrupo);
        } else if("A".equals(accion)){
            return grupoService.abandonar(idGrupo);
        }
        return IdResponse.builder().build();
    }
}
