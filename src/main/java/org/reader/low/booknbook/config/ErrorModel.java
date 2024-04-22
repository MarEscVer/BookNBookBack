package org.reader.low.booknbook.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorModel {

    @Schema(example = "400")
    private Integer httpCode;

    @Schema(example = "code_error")
    private String appError;

    @Schema(example = "Descripción corta del error")
    private String shortDescription;

    @Schema(example = "Descripción extendida del error")
    private String largeDescription;
}
