package org.reader.low.booknbook.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorModel {

    private Integer httpCode;

    private String appError;

    private String shortDescription;

    private String largeDescription;
}
