package org.reader.low.booknbook.controller.response.denuncia;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * The type Message valoracion.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Mensaje de valoracion",
        name = "firstName")
public class MessageValoracion {

    /**
     * The Message.
     */
    @Schema(description = "Mensaje de valoracion",
            name = "message",
            example = "Valoracion propia")
    private String message;
}
