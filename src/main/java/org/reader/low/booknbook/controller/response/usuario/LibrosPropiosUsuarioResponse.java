package org.reader.low.booknbook.controller.response.usuario;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.reader.low.booknbook.controller.object.LecturaUsuario;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LibrosPropiosUsuarioResponse {

    List<LecturaUsuario> libros;

    private PaginationInfo pageInfo;
}
