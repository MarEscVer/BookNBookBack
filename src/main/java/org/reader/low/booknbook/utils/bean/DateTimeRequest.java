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
public class DateTimeRequest {

    private DateRequest date;
    private TimeRequest time;

    @Override
    public String toString() {
        return date.getYear() + "-" + date.getMonth() + "-" + date.getDay()
                + " " +
                time.getHour() + ":" + time.getMinute() + ":" + time.getMinute();
    }
}
