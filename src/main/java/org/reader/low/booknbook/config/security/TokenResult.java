package org.reader.low.booknbook.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The type Token result.
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenResult {

    /**
     * The Bearer.
     */
    private String bearer;

    /**
     * The Password en cript.
     */
    private String passwordEnCript;
}
