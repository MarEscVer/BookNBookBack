package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.request.usuario.UpdatePerfilUsuario;
import org.reader.low.booknbook.controller.response.UsernameResponse;
import org.reader.low.booknbook.controller.response.usuario.PerfilUsuario;
import org.reader.low.booknbook.controller.response.usuario.PerfilUsuarioLibrosFavoritosResponse;
import org.reader.low.booknbook.controller.response.usuario.ValoracionPerfilUsuarioResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserController extends Controller{

    @Operation(summary = "Poner imagen al usuario", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping(value = "/imagen",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void crearUsuarioImagen(@RequestPart MultipartFile imagen) throws IOException;

    @Operation(summary = "Obtener el perfil del usuario", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = PerfilUsuario.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping(value = "/{username}/perfil")
    PerfilUsuario getPerfilUsuario(
            @Parameter(name="username", in = ParameterIn.PATH, description = "Usuario que quiere ver el perfil", required = true, example="string")
            @PathVariable(name="username") String username);

    @Operation(summary = "Actualizar el perfil del usuario logeado", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = UsernameResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping(value = "/perfil")
    UsernameResponse updatePerfilUsuario(@RequestBody UpdatePerfilUsuario request);

    @Operation(summary = "Obtener los libros favoritos de un usuario en el perfil", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = PerfilUsuarioLibrosFavoritosResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping(value = "/{username}/perfil/libros")
    PerfilUsuarioLibrosFavoritosResponse getListLibrosFavoritosUsuario(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="username", in = ParameterIn.PATH, description = "Usuario que quiere ver el perfil", required = true, example="string")
            @PathVariable(name="username") String username,
            @Parameter(name="filter", in = ParameterIn.QUERY, description = "valor para filtrar", example="5")
            @RequestParam(name="filter", required = false) String filter);

    @Operation(summary = "Obtener los comentarios que ha dejado un usuario, en us perfil", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ValoracionPerfilUsuarioResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping(value = "/{username}/perfil/valoracion")
    ValoracionPerfilUsuarioResponse getListValoracionPerfil(
            @Parameter(name="username", in = ParameterIn.PATH, description = "Usuario que quiere ver el perfil", required = true, example="string")
            @PathVariable(name="username") String username);
}
