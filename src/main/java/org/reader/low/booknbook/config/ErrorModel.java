package org.reader.low.booknbook.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Error model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorModel {

    /**
     * The Http code.
     */
    @Schema(example = "400")
    private Integer httpCode;

    /**
     * The App error.
     */
    @Schema(example = "code_error")
    private String appError;

    /**
     * The Short description.
     */
    @Schema(example = "Descripción corta del error")
    private String shortDescription;

    /**
     * The Large description.
     */
    @Schema(example = "Descripción extendida del error")
    private String largeDescription;
}
