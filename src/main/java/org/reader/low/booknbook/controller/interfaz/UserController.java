package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.constants.ApiConstants;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserController extends Controller{

    @Operation(summary = "Poner imagen al usuario", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping(value = "/imagen",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void crearAutorImagen(@RequestPart MultipartFile imagen) throws IOException;
}
