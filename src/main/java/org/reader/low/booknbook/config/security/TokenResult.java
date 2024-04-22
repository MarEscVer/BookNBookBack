package org.reader.low.booknbook.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenResult {

    String bearer;

    String passwordEnCript;
}
