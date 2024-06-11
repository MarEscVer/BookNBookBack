package org.reader.low.booknbook.controller.response.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.CalendarioLectura;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Calendario usuario response.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(
        description = "Respuesta de estadistica de calendario",
        
        name = "CalendarioUsuarioResponse")
public class CalendarioUsuarioResponse {

    /**
     * The Anyos.
     */
    @Schema(description = "Lista de años disponibles",
            name = "anyos",
            example = "[2022,2023]")
    private Set<Integer> anyos;

    /**
     * The Estadistica por anio.
     */
    @Schema(description = "Estadistica de calendario por año",
            
            name = "estadisticaPorAnio")
    private Map<Integer, List<CalendarioLectura>> estadisticaPorAnio;
}
