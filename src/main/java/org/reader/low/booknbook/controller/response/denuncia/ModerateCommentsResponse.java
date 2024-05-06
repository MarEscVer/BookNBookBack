package org.reader.low.booknbook.controller.response.denuncia;

import lombok.*;
import org.reader.low.booknbook.controller.object.ModerateComments;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModerateCommentsResponse {

    private String estado;

    private List<ModerateComments> comentariosDenunciados;
}
