package org.reader.low.booknbook.controller.response.usuario;

import lombok.*;
import org.reader.low.booknbook.controller.object.UserInfo;
import org.reader.low.booknbook.controller.response.PaginationInfo;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {

    List<UserInfo> usuarios;

    PaginationInfo pageInfo;
}
