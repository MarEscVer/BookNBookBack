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

    List<LibroDescripcion> masLeidos;

    List<LibroDescripcion> recomendados;

    List<LibroDescripcion> novedades;

    List<LibroDescripcion> listaFiltro;
}
