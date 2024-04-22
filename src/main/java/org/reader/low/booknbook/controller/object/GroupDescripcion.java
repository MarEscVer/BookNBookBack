package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDescripcion {

    private String nombre;

    private String descripcion;

    // valor , color
    private Map<String, String> genero;

    private Map<String, String> tipo;

    private BigDecimal miembros;

    private Boolean perteneces;
}
