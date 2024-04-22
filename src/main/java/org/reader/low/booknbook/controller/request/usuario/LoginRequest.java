package org.reader.low.booknbook.controller.request.usuario;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String username;

    private String password;
}
