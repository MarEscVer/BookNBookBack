package org.reader.low.booknbook.controller.request.grupo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupRequest {

    private String nombre;

    private List<Integer> generos;

    private List<Integer> tipos;

    private String descripcion;
}
