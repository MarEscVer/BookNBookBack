package org.reader.low.booknbook.controller.response;

import lombok.*;
import org.reader.low.booknbook.controller.object.LibroDescripcion;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListaLibrosRecomendadosResponse {

    private String filterName;

    private List<LibroDescripcion> libros;

    private PaginationInfo pageInfo;
}
