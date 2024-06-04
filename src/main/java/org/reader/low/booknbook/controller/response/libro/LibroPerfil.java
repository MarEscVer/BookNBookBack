package org.reader.low.booknbook.controller.response.libro;

import lombok.*;
import org.reader.low.booknbook.controller.object.Combo;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroPerfil {

    private Long id;

    private String titulo;

    private String saga;

    private Long idAutor;

    private String autor;

    private byte[] imagen;

    private Integer paginasTotales;

    private Date anyo;

    private BigDecimal calificacionMedia;

    private Combo genero;

    private Combo tipo;

    private String descripcion;

    private Integer contadorComentario;

    private String estado;
}
