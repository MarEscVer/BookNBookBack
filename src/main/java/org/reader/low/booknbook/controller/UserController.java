package org.reader.low.booknbook.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.constants.ApiConstants;
import org.reader.low.booknbook.config.ErrorModel;
import org.reader.low.booknbook.controller.request.usuario.LoginRequest;
import org.reader.low.booknbook.controller.request.usuario.RegisterRequest;
import org.reader.low.booknbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/user")
@ApiResponses(value = {
        @ApiResponse(description = ApiConstants.BAD_REQUEST, responseCode = ApiConstants.BAD_REQUEST_400, content = @Content(schema = @Schema(implementation = ErrorModel.class, description = ApiConstants.BAD_REQUEST, example = ApiConstants.BAD_REQUEST))),
        @ApiResponse(description = ApiConstants.UNAUTH, responseCode = ApiConstants.UNAUTH_401, content = @Content(schema = @Schema(implementation = ErrorModel.class, description = ApiConstants.UNAUTH, example = ApiConstants.UNAUTH))),
        @ApiResponse(description = ApiConstants.AUTHORIZATION, responseCode = ApiConstants.AUTHORIZATION_403, content = @Content(schema = @Schema(implementation = ErrorModel.class, description = ApiConstants.AUTHORIZATION, example = ApiConstants.AUTHORIZATION))),
        @ApiResponse(description = ApiConstants.NOTFOUND, responseCode = ApiConstants.NOTFOUND_404, content = @Content(schema = @Schema(implementation = ErrorModel.class, description = ApiConstants.NOTFOUND, example = ApiConstants.NOTFOUND))),
        @ApiResponse(description = ApiConstants.INSERE, responseCode = ApiConstants.INSERE_500, content = @Content(schema = @Schema(implementation = ErrorModel.class, description = ApiConstants.INSERE, example = ApiConstants.INSERE)))
})
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Create a new user", tags = {"Tutorial1 management", "Tutorial3"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Boolean.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @PostMapping("/register")
    public Boolean registerUser(
            @RequestBody
            RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @Operation(summary = "Validate that the user is registrered and login", tags = {"Tutorial1 management", "Tutorial3"})
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Boolean.class), mediaType = ApiConstants.JSON_RESPONSE) })
    @PostMapping("/login")
    public Boolean loginUser(
            @RequestBody
            LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
