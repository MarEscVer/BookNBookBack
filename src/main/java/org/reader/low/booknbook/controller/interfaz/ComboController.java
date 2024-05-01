package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.response.ComboResponse;
import org.reader.low.booknbook.controller.response.GeneroComboResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ComboController extends Controller {

    @Operation(summary = "Combo de sagas del autor", tags = {ApiConstants.TAG_COMBO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ComboResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @GetMapping("/saga/{idAutor}")
    public ComboResponse comboSaga(
            @Parameter(name="idAutor", in = ParameterIn.PATH, description = "El id del autor que se quiere recuperar", required = true, example="1")
            @PathVariable(name="idAutor", required = true) Long idAutor);

    @Operation(summary = "Combo de autores", tags = {ApiConstants.TAG_COMBO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ComboResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @GetMapping("/autor")
    ComboResponse comboAutores(
            @Parameter(name="filter", in = ParameterIn.QUERY, description = "filtro por pseudonimo")
            @RequestParam(name="filter", required = false)String filter);

    @Operation(summary = "Combo de genero y tipo", tags = {ApiConstants.TAG_COMBO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = GeneroComboResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @GetMapping("/genero")
    GeneroComboResponse comboGenero();
}
