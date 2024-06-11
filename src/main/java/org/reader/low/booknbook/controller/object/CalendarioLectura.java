package org.reader.low.booknbook.controller.object;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * The type Calendario lectura.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(
        description = "Estadistica de calendario",
        type = "object",
        name = "CalendarioLectura")
public class CalendarioLectura {

    /**
     * The Year.
     */
    @Schema(
            description = "Año",
            name = "year",
            type = "Integer",
            example = "2024")
    private Integer year;

    /**
     * The Month.
     */
    @Schema(
            description = "Mes",
            name = "month",
            type = "Integer",
            example = "5")
    private Integer month;

    /**
     * The Day.
     */
    @Schema(
            description = "Dia",
            name = "day",
            type = "Integer",
            example = "5")
    private Integer day;

    /**
     * The Paginas leidas.
     */
    @Schema(
            description = "Paginas leidas",
            name = "paginasLeidas",
            type = "Long",
            example = "55")
    private Long paginasLeidas;
}
