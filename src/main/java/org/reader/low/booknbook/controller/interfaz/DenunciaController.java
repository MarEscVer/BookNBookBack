package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.request.denuncia.DenunciaRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The interface Denuncia controller.
 */
public interface DenunciaController extends Controller {

    /**
     * Hacer denuncia id response.
     *
     * @param request the request
     * @return the id response
     */
    @Operation(summary = "Denunciar una valoracion", tags = {ApiConstants.TAG_DENUNCIA})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = IdResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping(value = "")
    IdResponse hacerDenuncia(@RequestBody DenunciaRequest request);
}
