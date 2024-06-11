package org.reader.low.booknbook.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The type Id response.
 */
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IdResponse {

    /**
     * The Id.
     */
    private Long id;

    /**
     * The Message.
     */
    private String message;
}
