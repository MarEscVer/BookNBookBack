package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.request.autor.AutorPerfilRequest;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AutorController  extends Controller {

    @Operation(summary = "Obtener los datos del perfil del Autor", tags = {ApiConstants.TAG_AUTOR})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AutorPerfilResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping("/pseudonimo")
    public AutorPerfilResponse getperfilAutor(
            @RequestBody
            AutorPerfilRequest autorPerfilRequest);
}
