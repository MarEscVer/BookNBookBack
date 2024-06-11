package org.reader.low.booknbook.controller.request.usuario;

import lombok.*;

/**
 * The type Rol request.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RolRequest {
    /**
     * The Username.
     */
    private String username;

    /**
     * The Rol.
     */
    private String rol;
}
