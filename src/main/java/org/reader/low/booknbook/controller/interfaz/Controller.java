package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.reader.low.booknbook.config.ErrorModel;
import org.reader.low.booknbook.constants.ApiConstants;


/**
 * The interface Controller.
 */
@ApiResponses(value = {
        @ApiResponse(description = ApiConstants.BAD_REQUEST, responseCode = ApiConstants.BAD_REQUEST_400, content = @Content(schema = @Schema(implementation = ErrorModel.class, description = ApiConstants.BAD_REQUEST, example = ApiConstants.BAD_REQUEST),mediaType = ApiConstants.JSON_RESPONSE)),
        @ApiResponse(description = ApiConstants.UNAUTH, responseCode = ApiConstants.UNAUTH_401, content = @Content(schema = @Schema(implementation = ErrorModel.class, description = ApiConstants.UNAUTH, example = ApiConstants.UNAUTH))),
        @ApiResponse(description = ApiConstants.AUTHORIZATION, responseCode = ApiConstants.AUTHORIZATION_403, content = @Content(schema = @Schema(implementation = ErrorModel.class, description = ApiConstants.AUTHORIZATION, example = ApiConstants.AUTHORIZATION))),
        @ApiResponse(description = ApiConstants.NOTFOUND, responseCode = ApiConstants.NOTFOUND_404, content = @Content(schema = @Schema(implementation = ErrorModel.class, description = ApiConstants.NOTFOUND, example = ApiConstants.NOTFOUND))),
        @ApiResponse(description = ApiConstants.INSERE, responseCode = ApiConstants.INSERE_500, content = @Content(schema = @Schema(implementation = ErrorModel.class, description = ApiConstants.INSERE, example = ApiConstants.INSERE)))
})
public interface Controller {
}
