package org.reader.low.booknbook.controller.interfaz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Pattern;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.controller.request.grupo.CreateGroupRequest;
import org.reader.low.booknbook.controller.response.DeleteResponse;
import org.reader.low.booknbook.controller.response.grupo.ListGrupoResponse;
import org.reader.low.booknbook.controller.response.grupo.ListNameGrupoResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GrupoController extends Controller {

    @Operation(summary = "Crear un nuevo grupo", tags = {ApiConstants.TAG_GRUPO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping
    public void createGroup(@RequestBody CreateGroupRequest createGroupRequest) throws IOException;

    @Operation(summary = "Poner imagen al grupo", tags = {ApiConstants.TAG_GRUPO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping(value = "/imagen", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void createGroupImagen(
            @Parameter(name="idGrupo", in = ParameterIn.QUERY, description = "Grupo al que se asigna la imagen", required = true, example="1")
            @RequestParam(name="idGrupo")Long idGrupo,
            @RequestPart MultipartFile imagen) throws IOException;

    @Operation(summary = "Obtener los grupos que el usuario tiene", tags = {ApiConstants.TAG_GRUPO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ListNameGrupoResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping("/mis-clubes")
    public ListNameGrupoResponse getListGroup(
            @Parameter(name="type", in = ParameterIn.QUERY, description = "Tipo de lista que quiere obtener", required = true, example="P")
            @Pattern(regexp = "P|A")
            @RequestParam(name="type", required = true) String type,
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="filter", in = ParameterIn.QUERY, description = "valor para filtrar", example="5")
            @RequestParam(name="filter", required = false) String filter);

    @Operation(summary = "Obtener los grupos", tags = {ApiConstants.TAG_GRUPO})
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

    @Operation(summary = "Obtener los grupos que el usuario tiene", tags = {ApiConstants.TAG_GRUPO})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ListNameGrupoResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @DeleteMapping("/{idGrupo}")
    public DeleteResponse deleteMyGroup(
            @Parameter(name="idGrupo", in = ParameterIn.PATH, description = "El grupo que quiere eliminar", example="1")
            @RequestParam(name="idGrupo", required = true) Long idGrupo);
}
