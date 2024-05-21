package org.reader.low.booknbook.controller.response.grupo;

import lombok.*;
import org.reader.low.booknbook.controller.object.GroupDescripcion;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListGrupoResponse {

    private List<GroupDescripcion> listGroup;

    private PaginationInfo pageInfo;
}
