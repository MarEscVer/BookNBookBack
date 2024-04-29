package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface AutorController extends Controller {

    @Operation(summary = "Obtener los datos del perfil del Autor", tags = {ApiConstants.TAG_AUTOR})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AutorPerfilResponse.class), mediaType = ApiConstants.JSON_RESPONSE)})
    @GetMapping("/{idAutor}")
    public AutorPerfilResponse getperfilAutor(
            @Parameter(name="idAutor", in = ParameterIn.PATH, description = "El id del autor que se quiere recuperar", required = true, example="1")
            @PathVariable(name="idAutor", required = true) Long idAutor);
}
