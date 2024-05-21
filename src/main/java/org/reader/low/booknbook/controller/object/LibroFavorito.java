package org.reader.low.booknbook.controller.object;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroFavorito {

    private Long id;

    private String nombre;

    private Date fechaPublicacion;

    private byte[] fotoLibro;

    private String saga;

    private Integer paginasLibro;

    private Integer valoracion;


}
