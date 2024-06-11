package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.request.usuario.UpdatePerfilUsuario;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.UsernameResponse;
import org.reader.low.booknbook.controller.response.usuario.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The interface User controller.
 */
public interface UserController extends Controller{

    /**
     * Crear usuario imagen.
     *
     * @param imagen the imagen
     * @throws IOException the io exception
     */
    @Operation(summary = "Poner imagen al usuario", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping(value = "/imagen",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void crearUsuarioImagen(@RequestPart MultipartFile imagen) throws IOException;

    /**
     * Gets perfil usuario.
     *
     * @param username the username
     * @return the perfil usuario
     */
    @Operation(summary = "Obtener el perfil del usuario", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = PerfilUsuario.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping(value = "/{username}/perfil")
    PerfilUsuario getPerfilUsuario(
            @Parameter(name="username", in = ParameterIn.PATH, description = "Usuario que quiere ver el perfil", required = true, example="string")
            @PathVariable(name="username") String username);

    /**
     * Update perfil usuario username response.
     *
     * @param request the request
     * @return the username response
     */
    @Operation(summary = "Actualizar el perfil del usuario logeado", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = UsernameResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping(value = "/perfil")
    UsernameResponse updatePerfilUsuario(@RequestBody UpdatePerfilUsuario request);

    /**
     * Gets list libros favoritos usuario.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param username  the username
     * @param filter    the filter
     * @return the list libros favoritos usuario
     */
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

    /**
     * Gets list valoracion perfil.
     *
     * @param username the username
     * @return the list valoracion perfil
     */
    @Operation(summary = "Obtener los comentarios que ha dejado un usuario, en us perfil", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ValoracionPerfilUsuarioResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping(value = "/{username}/perfil/valoracion")
    ValoracionPerfilUsuarioResponse getListValoracionPerfil(
            @Parameter(name="username", in = ParameterIn.PATH, description = "Usuario que quiere ver el perfil", required = true, example="string")
            @PathVariable(name="username") String username);

    /**
     * Delete usuario username response.
     *
     * @return the username response
     */
    @Operation(summary = "Desabilitar su cuenta", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = UsernameResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @DeleteMapping(value = "/self/desactivacion")
    UsernameResponse deleteUsuario();

    /**
     * Libros propios libros propios usuario response.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param estado    the estado
     * @return the libros propios usuario response
     */
    @Operation(summary = "Obtener los libros segun el estado en el que lo tengan", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = LibrosPropiosUsuarioResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping(value = "/libros/{estado}")
    LibrosPropiosUsuarioResponse librosPropios(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="estado", in = ParameterIn.PATH, description = "El estado de lectura", example="LEIDO")
            @PathVariable(name="estado", required = false) String estado);

    /**
     * Seguir usuario id response.
     *
     * @param username the username
     * @return the id response
     */
    @Operation(summary = "Seguir a otro usuario", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = IdResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping(value = "/usuario/{username}/follow")
    IdResponse seguirUsuario(
            @Parameter(name="username", in = ParameterIn.PATH, description = "El usuario al que desea seguir", example="string")
            @PathVariable(name="username", required = false) String username);

    /**
     * No seguir usuario id response.
     *
     * @param username the username
     * @return the id response
     */
    @Operation(summary = "Dejar de seguir a otro usuario", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = {
            @Content(schema = @Schema(implementation = IdResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping(value = "/usuario/{username}/unfollow")
    IdResponse noSeguirUsuario(
            @Parameter(name="username", in = ParameterIn.PATH, description = "El usuario al que desea dejar de seguir", example="string")
            @PathVariable(name="username", required = false) String username);

    /**
     * Estadistica usuario contador usuario response.
     *
     * @return the contador usuario response
     */
    @Operation(summary = "Estadistica del usuario", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ContadorUsuarioResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping(value = "/estadistica")
    ContadorUsuarioResponse estadisticaUsuario();

    /**
     * Estadistica calendario usuario calendario usuario response.
     *
     * @param anyoSelected the anyo selected
     * @return the calendario usuario response
     */
    @Operation(summary = "Paginas leidas por el usuario cada dia", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CalendarioUsuarioResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping(value = "/estadistica/calendario")
    CalendarioUsuarioResponse estadisticaCalendarioUsuario(
            @Parameter(name="anyoSelected", in = ParameterIn.QUERY, description = "El año que quiere recuperar", example="2022")
            @RequestParam(name="anyoSelected", required = false) Integer anyoSelected);

    /**
     * Estadistica genero usuario estadistica genero response.
     *
     * @return the estadistica genero response
     */
    @Operation(summary = "Libros leidos por genero", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = EstadisticaGeneroResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping(value = "/estadistica/genero")
    EstadisticaGeneroResponse estadisticaGeneroUsuario();

    /**
     * Estadistica estado libro estadistica genero response.
     *
     * @return the estadistica genero response
     */
    @Operation(summary = "Libros guardados por estado", tags = {ApiConstants.TAG_USUARIO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = EstadisticaGeneroResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping(value = "/estadistica/estado")
    EstadisticaGeneroResponse estadisticaEstadoLibro();
}
