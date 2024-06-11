package org.reader.low.booknbook.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The type Username response.
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsernameResponse {

    /**
     * The Id.
     */
    private String id;

    /**
     * The Message.
     */
    private String message;
}
