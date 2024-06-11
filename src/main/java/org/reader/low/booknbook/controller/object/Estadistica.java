package org.reader.low.booknbook.controller.object;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The type Estadistica.
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(
        description = "Objeto de estadistica",
        name = "Estadistica")
public class Estadistica {

    /**
     * The Titulo.
     */
    @Schema(
            description = "Label estadistica",
            name = "titulo",
            type = "string",
            example = "TITULO")
    private String titulo;

    /**
     * The Dato.
     */
    @Schema(
            description = "Valor estadistica",
            name = "dato",
            type = "Long",
            example = "35")
    private Long dato;
}
