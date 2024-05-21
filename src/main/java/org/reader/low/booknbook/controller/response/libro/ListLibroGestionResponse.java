package org.reader.low.booknbook.controller.response.libro;

import lombok.*;
import org.reader.low.booknbook.controller.object.LibroGestion;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListLibroGestionResponse {

    List<LibroGestion> libros;

    PaginationInfo pageInfo;
}
