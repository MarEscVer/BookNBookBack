package org.reader.low.booknbook.controller.response.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.reader.low.booknbook.controller.object.Estadistica;

import java.util.List;

/**
 * The type Estadistica genero response.
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(
        description = "Respuesta de la lista de valores para las estadisticas",
        name = "EstadisticaGeneroResponse")
public class EstadisticaGeneroResponse {

    /**
     * The Estadisticas.
     */
    @Schema(description = "Lista de valores para las estadisticas",
            name = "estadisticas")
    private List<Estadistica> estadisticas;
}
