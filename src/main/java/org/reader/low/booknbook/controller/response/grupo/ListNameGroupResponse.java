package org.reader.low.booknbook.controller.response.grupo;

import lombok.*;
import org.reader.low.booknbook.controller.object.NombreGrupos;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListNameGroupResponse {

    private List<NombreGrupos> nombreGrupos;

}
