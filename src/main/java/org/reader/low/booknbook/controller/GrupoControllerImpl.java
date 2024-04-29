package org.reader.low.booknbook.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.BadRequestHanderException;
import org.reader.low.booknbook.constants.Constants;
import org.reader.low.booknbook.controller.interfaz.GrupoController;
import org.reader.low.booknbook.controller.response.DeleteResponse;
import org.reader.low.booknbook.controller.request.grupo.CreateGroupRequest;
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

@Slf4j
@NoArgsConstructor
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/grupo")
public class GrupoControllerImpl implements GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Override
    public void createGroup(Long idGrupo, MultipartFile createGroupRequest) throws IOException {
        grupoService.setImageToGroup(idGrupo, createGroupRequest);
    }

    @Override
    public void createGroup(CreateGroupRequest createGroupRequest) throws IOException {
        grupoService.createGroup(createGroupRequest);
    }

    @Override
    public ListGrupoResponse getListGrupos(Integer pageIndex, Integer size, String filter) {
        if (!StringUtils.hasText(filter)){
            filter = "";
        }
        return grupoService.getListGroup(pageIndex, size, filter);
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
}