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
import org.reader.low.booknbook.controller.response.ListaLibrosRecomendadosResponse;
import org.reader.low.booknbook.controller.response.LoginResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.grupo.ListNameGrupoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface DefaultUserController extends Controller {

    @Operation(summary = "Create a new user", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TokenResult.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @PostMapping("/token")
    public TokenResult token(
            @RequestBody
            AuthCredentials requestCredentials, @RequestParam(name = "gen", required = true, defaultValue = "false") boolean gen);

    @Operation(summary = "Create a new user", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping("/register")
    public void registerUser(
            @RequestBody
            RegisterRequest registerRequest);

    @Operation(summary = "Validate that the user is registrered and login", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = LoginResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @PostMapping("/login")
    public LoginResponse loginUser(
            @RequestBody
            LoginRequest loginRequest);

    @Operation(summary = "Obtener los libros recomendados", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = LoginResponse.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @GetMapping("/libros-recomendados")
    public ListaLibrosRecomendadosResponse listaLibrosRecomendados(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="filter", in = ParameterIn.QUERY, description = "valor para filtrar", example="5")
            @RequestParam(name="filter", required = false) String filter
            );

    @Operation(summary = "Obtener los grupos", tags = {ApiConstants.TAG_PUBLICO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ListNameGrupoResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping("/clubes")
    ListGrupoResponse getListGrupos(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="filter", in = ParameterIn.QUERY, description = "valor para filtrar", example="5")
            @RequestParam(name="filter", required = false) String filter);

}
