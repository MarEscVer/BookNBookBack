package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.request.libro.PuntuarLibroRequest;
import org.reader.low.booknbook.controller.response.valoracion.ValoracionResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The interface Libro controller.
 */
public interface LibroController extends Controller {

    /**
     * Puntuar libro.
     *
     * @param puntuarLibroRequest the puntuar libro request
     */
    @Operation(summary = "Puntuar un libro", tags = {ApiConstants.TAG_LIBRO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping("/puntuar")
    void puntuarLibro(
            @RequestBody PuntuarLibroRequest puntuarLibroRequest);

    /**
     * Guardar libro valoracion valoracion response.
     *
     * @param idLibro the id libro
     * @param estado  the estado
     * @return the valoracion response
     */
    @Operation(summary = "Vincular un libro con el usuario", tags = {ApiConstants.TAG_LIBRO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ValoracionResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping("{idLibro}/valoracion/{estado}")
    ValoracionResponse guardarLibroValoracion(
            @Parameter(name="idLibro", in = ParameterIn.PATH, description = "Libro que se desea vincular", required = true, example="1")
            @PathVariable(name="idLibro") Long idLibro,
            @Parameter(name="estado", in = ParameterIn.PATH, description = "Estado que tiene el usuario con la lectura del libro", required = true, example="LEIDO")
            @PathVariable(name="estado") String estado);

    /**
     * Actualizar libro valoracion valoracion response.
     *
     * @param request the request
     * @return the valoracion response
     */
    @Operation(summary = "Actualizar la valoracion de un usuario sobre un libro", tags = {ApiConstants.TAG_LIBRO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ValoracionResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping("/valoracion")
    ValoracionResponse actualizarLibroValoracion(@RequestBody ValoracionResponse request);
}
