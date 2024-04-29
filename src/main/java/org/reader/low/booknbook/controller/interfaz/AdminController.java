package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.request.autor.CreateAutorRequest;
import org.reader.low.booknbook.controller.request.libro.CreateLibroRequest;
import org.reader.low.booknbook.controller.response.denuncia.ModerateCommentsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AdminController extends Controller {

    @Operation(summary = "Crear un nuevo libro", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping("/libro")
    void crearLibro(@RequestBody CreateLibroRequest createLibroRequest);

    @Operation(summary = "Crear un nuevo autor", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping("/autor")
    void crearAutor(@RequestBody CreateAutorRequest createAutorRequest);

    @Operation(summary = "Crear un nuevo autor", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ModerateCommentsResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping("/comentarios/denuncia")
    ModerateCommentsResponse listaComentariosDenunciados(@Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
                                     @RequestParam(name="pageIndex") Integer pageIndex,
                                                         @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
                                     @RequestParam(name="size") Integer size,
                                                         @Parameter(name="filter", in = ParameterIn.QUERY, description = "valor para filtrar", example="5")
                                     @RequestParam(name="filter", required = false) String filter);

}
