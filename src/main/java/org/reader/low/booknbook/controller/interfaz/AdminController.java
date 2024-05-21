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
import org.reader.low.booknbook.controller.request.libro.UpdateLibroRequest;
import org.reader.low.booknbook.controller.request.usuario.RolRequest;
import org.reader.low.booknbook.controller.response.IdResponse;
import org.reader.low.booknbook.controller.response.MessageResponse;
import org.reader.low.booknbook.controller.response.denuncia.MessageValoracion;
import org.reader.low.booknbook.controller.response.denuncia.ModerateCommentsResponse;
import org.reader.low.booknbook.controller.response.libro.ListLibroGestionResponse;
import org.reader.low.booknbook.controller.response.usuario.UserInfoResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

public interface AdminController extends Controller {

    @Operation(summary = "Crear un nuevo libro", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = MessageResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping("/libro")
    MessageResponse crearLibro(@RequestBody CreateLibroRequest createLibroRequest);

    @Operation(summary = "Poner imagen al libro", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping(value = "/libro/{idLibro}/imagen",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void crearLibroImagen(
            @Parameter(name="idLibro", in = ParameterIn.PATH, description = "Libro al que se asigna la imagen", required = true, example="1")
            @PathVariable(name="idLibro")Long idLibro,
            @RequestPart MultipartFile imagen) throws IOException;

    @Operation(summary = "Crear un nuevo autor", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = IdResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PostMapping("/autor")
    IdResponse crearAutor(@RequestBody CreateAutorRequest createAutorRequest);

    @Operation(summary = "Poner imagen al autor", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping(value = "/autor/{idAutor}/imagen",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void crearAutorImagen(
            @Parameter(name="idAutor", in = ParameterIn.PATH, description = "Autor al que se asigna la imagen", required = true, example="1")
            @PathVariable(name="idAutor")Long idAutor,
            @RequestPart MultipartFile imagen) throws IOException, SQLException;

    @Operation(summary = "Lista de comentarios denunciados", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ModerateCommentsResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping("/comentarios/denuncia")
    ModerateCommentsResponse listaComentariosDenunciados(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="filter", in = ParameterIn.QUERY, description = "valor para filtrar", example="5")
            @RequestParam(name="filter", required = false) String filter,
            @Parameter(name="estado", in = ParameterIn.QUERY, description = "estado de la denuncia", example="5")
            @RequestParam(name="estado", required = false)
            String estado);


    @Operation(summary = "Establecer un estado a la denuncia", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping("/denuncia/{idDenuncia}/{estado}")
    void cambiarEstadoDenuncia(
            @Parameter(name="idDenuncia", in = ParameterIn.PATH, description = "El id de la denuncia", required = true, example="1")
            @PathVariable(name="idDenuncia") Long idDenuncia,
            @Parameter(name="estado", in = ParameterIn.PATH, description = "El id de la denuncia", required = true, example="1")
            @PathVariable(name="estado")
            String estado);

    @Operation(summary = "Cambiar el rol a un usuario", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = void.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping("/usuario/rol")
    void cambiarRolUsuario(@RequestBody RolRequest request);

    @Operation(summary = "Obtener los usuarios de la aplicacion", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = UserInfoResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping("/usuario/lista")
    UserInfoResponse getUsuarioInfo(
            @Parameter(name="username", in = ParameterIn.QUERY, description = "valor para filtrar", example="str")
            @RequestParam(name="username", required = false) String username,
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size);

    @Operation(summary = "Obtener la valoración denunciado", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = MessageValoracion.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping("/denuncia/valoracion/mensaje")
    MessageValoracion valoracionMessage(
            @Parameter(name="idLibro", in = ParameterIn.QUERY, description = "Id del libro que compone el id de valoracion", required = true, example="0")
            @RequestParam(name="idLibro") Long idLibro,
            @Parameter(name="idUsuario", in = ParameterIn.QUERY, description = "Id del usuario que compone el id de valoracion", required = true, example="0")
            @RequestParam(name="idUsuario") Long idUsuario);

    @Operation(summary = "Obtener los libros para gestionarlos", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ListLibroGestionResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @GetMapping("/libro/gestion")
    ListLibroGestionResponse getListLibros(
            @Parameter(name="pageIndex", in = ParameterIn.QUERY, description = "La página que quiere recuperar", required = true, example="0")
            @RequestParam(name="pageIndex") Integer pageIndex,
            @Parameter(name="size", in = ParameterIn.QUERY, description = "El tamaño de la lista por página", required = true, example="5")
            @RequestParam(name="size") Integer size,
            @Parameter(name="filtro", in = ParameterIn.QUERY, description = "La página que quiere recuperar", example="0")
            @RequestParam(name="filtro", required = false)
            String filtro);

    @Operation(summary = "Actualizar un libro", tags = {ApiConstants.TAG_ADMIN})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = IdResponse.class), mediaType = ApiConstants.JSON_RESPONSE),  })
    @PutMapping("/libro")
    IdResponse updateLibro(@RequestBody UpdateLibroRequest request);
}
