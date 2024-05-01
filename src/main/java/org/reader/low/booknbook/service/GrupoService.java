package org.reader.low.booknbook.service;

import org.reader.low.booknbook.controller.request.grupo.CreateGroupRequest;
import org.reader.low.booknbook.controller.response.DeleteResponse;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.grupo.ListNameGrupoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GrupoService {

    ListGrupoResponse getListGroup(Integer pageIndex, Integer size, String filter, boolean needToken);

    //ListGrupoResponse getListGroup(Integer pageIndex, Integer size, String filter);

    ListNameGrupoResponse getListGrupoPropios(String type, Integer pageIndex, Integer size, String filter);

    DeleteResponse deleteGroup(Long idGrupo);

    IdResponse createGroup(CreateGroupRequest createGroupRequest) throws IOException;

    void setImageToGroup(Long idGrupo, MultipartFile imagen) throws IOException;
}
