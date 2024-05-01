package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LibroObject {
    private Long id;

    private String nombre;

    private String descripcion;

    private Date fechaPublicacion;

    private byte[] fotoLibro;

    private String saga;

    private Integer grupos;

    private Integer paginasLibro;

    private BigDecimal valoracion;

    private Integer comentarios;
}
