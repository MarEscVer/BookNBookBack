package org.reader.low.booknbook.controller.response.libro;

import lombok.*;
import org.reader.low.booknbook.controller.object.ValoracionUsuario;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioPerfilLibroResponse {

    List<ValoracionUsuario> valoraciones;

    PaginationInfo pageInfo;
}
