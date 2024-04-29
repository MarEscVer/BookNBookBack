package org.reader.low.booknbook.config.security.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooknBookInfo {

    private String timestamp;

    private Locale locale;

    private String appName;
}
