package org.reader.low.booknbook.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The type Login response.
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {

    /**
     * The Username.
     */
    private String username;

    /**
     * The Rol.
     */
    private String rol;

    /**
     * The Bearer.
     */
    private String bearer;
}
