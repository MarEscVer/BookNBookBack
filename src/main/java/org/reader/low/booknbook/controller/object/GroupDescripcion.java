package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDescripcion {

    private Long id;

    private String nombre;

    private String descripcion;
    // valor , color
    private Map<String, String> genero;
    // valor , color
    private Map<String, String> tipo;

    private Integer miembros;

    private Boolean perteneces;

    private byte[] imagen;
}
