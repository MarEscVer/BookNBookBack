package org.reader.low.booknbook.controller.response.grupo;

import lombok.*;
import org.reader.low.booknbook.controller.object.GroupDescripcion;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListGrupoResponse {

    List<GroupDescripcion> listGroup;
}
