package org.reader.low.booknbook.utils.bean;

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
public class TimeRequest {

    private Integer hour;
    private Integer minute;
    private Integer second;
}
