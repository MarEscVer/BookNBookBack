package org.reader.low.booknbook.controller.object;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDescripcion {

    private Long id;

    private String nombre;

    private String descripcion;

    private ComboGenero genero;

    private ComboGenero tipo;

    private Integer miembros;

    private Boolean perteneces;

    private byte[] imagen;
}
