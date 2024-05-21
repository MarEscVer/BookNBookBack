package org.reader.low.booknbook.controller.request.usuario;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RolRequest {
    private String username;

    private String rol;
}
