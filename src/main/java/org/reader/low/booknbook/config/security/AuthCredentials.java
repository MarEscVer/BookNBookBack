package org.reader.low.booknbook.config.security;

import lombok.Data;

/**
 * The type Auth credentials.
 */
@Data
public class AuthCredentials {

    /**
     * The Username.
     */
    private String username;

    /**
     * The Password.
     */
    private String password;
}
