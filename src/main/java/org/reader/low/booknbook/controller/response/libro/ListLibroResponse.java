package org.reader.low.booknbook.controller.response.libro;

import lombok.*;
import org.reader.low.booknbook.controller.object.LibroDescripcion;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListLibroResponse {

    private List<LibroDescripcion> listLibros;
}
