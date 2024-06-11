package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.reader.low.booknbook.config.security.AuthCredentials;
import org.reader.low.booknbook.config.security.TokenResult;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.request.usuario.LoginRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.controller.response.ContadorResponse;
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;
import org.reader.low.booknbook.controller.response.LoginResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilLibrosResponse;
import org.reader.low.booknbook.controller.response.autor.AutorPerfilResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.libro.ComentarioPerfilLibroResponse;
import org.reader.low.booknbook.controller.response.libro.LibroPerfil;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The interface Default user controller.
 */
public interface DefaultUserController extends Controller {

    /**
     * Token token result.
     *
     * @param requestCredentials the request credentials
     * @param gen                the gen
     * @return the token result
     */
    @Operation(summary = "Obtener el token de un usuario registrado", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TokenResult.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @PostMapping("/token")
    TokenResult token(
            @RequestBody
            AuthCredentials requestCredentials, @RequestParam(name = "gen", required = true, defaultValue = "false") boolean gen);

    /**
     * Register user.
     *
     * @param registerRequest the register request
     */
    @Operation(summary = "Crear un nuevo usuario", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping("/register")
    void registerUser(
            @RequestBody
            RegisterRequest registerRequest);

    /**
     * Login user login response.
     *
     * @param loginRequest the login request
     * @return the login response
     */
    @Operation(summary = "Iniciar sesion con un usuario registrado", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = LoginResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @PostMapping("/login")
    LoginResponse loginUser(
            @RequestBody
            LoginRequest loginRequest);

    /**
     * Gets libro perfil.
     *
     * @param idLibro the id libro
     * @return the libro perfil
     */
    @Operation(summary = "Obtener el perfil de un libro", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = LibroPerfil.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @GetMapping("/libro/{idLibro}")
    LibroPerfil getLibroPerfil(
            @Parameter(name="idLibro", in = ParameterIn.PATH, description = "Libro al que se asigna la imagen", required = true, example="1")
            @PathVariable(name="idLibro")Long idLibro);

    /**
     * Lista libros lista libros recomendados response.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param genero    the genero
     * @param filter    the filter
     * @return the lista libros recomendados response
     */
    @Operation(summary = "Obtener los libros recomendados", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ListaLibrosRecomendadosResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @GetMapping("/libros")
    ListaLibrosRecomendadosResponse listaLibros(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="genero", in = ParameterIn.QUERY, description = "Genero para filtrar", example="5")
            @RequestParam(name="genero", required = false) String genero,
            @Parameter(name="filter", in = ParameterIn.QUERY, description = "valor para filtrar", example="5")
            @RequestParam(name="filter", required = false)String filter);

    /**
     * Lista libros recomendados lista libros recomendados response.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param genero    the genero
     * @return the lista libros recomendados response
     */
    @Operation(summary = "Obtener los libros recomendados", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ListaLibrosRecomendadosResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @GetMapping("/libros-recomendados")
    ListaLibrosRecomendadosResponse listaLibrosRecomendados(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="genero", in = ParameterIn.QUERY, description = "Genero para filtrar", example="5")
            @RequestParam(name="genero", required = false) String genero
            );

    /**
     * Lista libros novedades lista libros recomendados response.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param genero    the genero
     * @return the lista libros recomendados response
     */
    @Operation(summary = "Obtener los libros novedosos", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ListaLibrosRecomendadosResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @GetMapping("/libros-novedades")
    ListaLibrosRecomendadosResponse listaLibrosNovedades(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="genero", in = ParameterIn.QUERY, description = "Genero para filtrar", example="5")
            @RequestParam(name="genero", required = false) String genero
    );

    /**
     * Lista libros leidos lista libros recomendados response.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param genero    the genero
     * @return the lista libros recomendados response
     */
    @Operation(summary = "Obtener los libros más leidos", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ListaLibrosRecomendadosResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @GetMapping("/libros-mas-leidos")
    ListaLibrosRecomendadosResponse listaLibrosLeidos(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="genero", in = ParameterIn.QUERY, description = "Genero para filtrar", example="5")
            @RequestParam(name="genero", required = false) String genero
    );

    /**
     * Lista libros aleatorios lista libros recomendados response.
     *
     * @param size   the size
     * @param genero the genero
     * @return the lista libros recomendados response
     */
    @Operation(summary = "Obtener los libros aleatorios", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ListaLibrosRecomendadosResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @GetMapping("/libros-propuestas")
    ListaLibrosRecomendadosResponse listaLibrosAleatorios(
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="genero", in = ParameterIn.QUERY, description = "Genero para filtrar", example="5")
            @RequestParam(name="genero", required = false) String genero
    );

    /**
     * Gets list grupos.
     *
     * @param pageIndex the page index
     * @param size      the size
     * @param filter    the filter
     * @return the list grupos
     */
    @Operation(summary = "Obtener los grupos", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ListGrupoResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping("/clubes")
    ListGrupoResponse getListGrupos(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="filter", in = ParameterIn.QUERY, description = "valor para filtrar", example="5")
            @RequestParam(name="filter", required = false) String filter);

    /**
     * Contador contador response.
     *
     * @return the contador response
     */
    @Operation(summary = "Datos generales de la aplicacion", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ContadorResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @PostMapping("/contador")
    ContadorResponse contador();

    /**
     * Gets autor.
     *
     * @param idAutor the id autor
     * @return the autor
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    @Operation(summary = "Obtener los datos del perfil del Autor", tags = {ApiConstants.TAG_AUTOR})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AutorPerfilResponse.class), mediaType = ApiConstants.JSON_RESPONSE)})
    @GetMapping("/autor/{idAutor}")
    AutorPerfilResponse getperfilAutor(
            @Parameter(name="idAutor", in = ParameterIn.PATH, description = "El id del autor que se quiere recuperar", required = true, example="1")
            @PathVariable(name="idAutor", required = true) Long idAutor) throws SQLException, IOException;

    /**
     * Gets autor libros.
     *
     * @param idAutor the id autor
     * @return the autor libros
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    @Operation(summary = "Obtener los libros del Autor", tags = {ApiConstants.TAG_AUTOR})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AutorPerfilLibrosResponse.class), mediaType = ApiConstants.JSON_RESPONSE)})
    @GetMapping("/autor/{idAutor}/libros")
    AutorPerfilLibrosResponse getperfilAutorLibros(
            @Parameter(name="idAutor", in = ParameterIn.PATH, description = "El id del autor que se quiere recuperar", required = true, example="1")
            @PathVariable(name="idAutor", required = true) Long idAutor) throws SQLException, IOException;

    /**
     * Gets perfil libro comentarios.
     *
     * @param idLibro   the id libro
     * @param pageIndex the page index
     * @param size      the size
     * @return the perfil libro comentarios
     */
    @Operation(summary = "Obtener los comentarios del libro", tags = {ApiConstants.TAG_LIBRO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ComentarioPerfilLibroResponse.class), mediaType = ApiConstants.JSON_RESPONSE)})
    @GetMapping("/libro/{idLibro}/comentarios")
    ComentarioPerfilLibroResponse getPerfilLibroComentarios(
            @Parameter(name="idLibro", in = ParameterIn.PATH, description = "El id del autor que se quiere recuperar", required = true, example="1")
            @PathVariable(name="idLibro", required = true) Long idLibro,
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size);

}
