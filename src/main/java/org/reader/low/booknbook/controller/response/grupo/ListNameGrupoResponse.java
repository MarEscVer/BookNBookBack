package org.reader.low.booknbook.controller.response.grupo;

import lombok.*;
import org.reader.low.booknbook.controller.object.NombreGrupos;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListNameGrupoResponse {

    private List<NombreGrupos> nombreGrupos;

    private PaginationInfo pageInfo;

}
