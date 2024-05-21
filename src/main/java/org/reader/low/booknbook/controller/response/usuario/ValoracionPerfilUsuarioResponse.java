package org.reader.low.booknbook.controller.response.usuario;

import lombok.*;
import org.reader.low.booknbook.controller.object.ValoracionUsuario;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionPerfilUsuarioResponse {

    List<ValoracionUsuario> valoraciones;
}
