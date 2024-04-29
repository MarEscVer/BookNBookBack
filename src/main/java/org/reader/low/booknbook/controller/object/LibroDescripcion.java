package org.reader.low.booknbook.controller.object;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibroDescripcion {

    private MultipartFile imagen;

    private String saga;

    private String titulo;

    private String autor;
}
