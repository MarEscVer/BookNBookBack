package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface LibroController extends Controller {

    @Operation(summary = "Puntuar un libro", tags = {ApiConstants.TAG_LIBRO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping("/puntuar")
    public void puntuarLibro(
            @RequestBody PuntuarLibroRequest puntuarLibroRequest);



}
