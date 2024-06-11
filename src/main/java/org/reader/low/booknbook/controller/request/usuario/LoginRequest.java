package org.reader.low.booknbook.controller.request.usuario;

import lombok.*;

/**
 * The type Login request.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    /**
     * The Username.
     */
    private String username;

    /**
     * The Password.
     */
    private String password;
}
