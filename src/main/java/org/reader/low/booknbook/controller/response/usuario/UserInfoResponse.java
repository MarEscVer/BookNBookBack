package org.reader.low.booknbook.controller.response.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.reader.low.booknbook.controller.object.UserInfo;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

/**
 * The type User info response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {

    /**
     * The Usuarios.
     */
    private List<UserInfo> usuarios;

    /**
     * The Page info.
     */
    @Schema(description = "Estado del libro denunciado",
            name = "pageInfo")
    private PaginationInfo pageInfo;
}
