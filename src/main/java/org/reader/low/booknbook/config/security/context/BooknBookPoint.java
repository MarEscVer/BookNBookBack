package org.reader.low.booknbook.config.security.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.Map;

@Builder(toBuilder = true)
@Jacksonized
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BooknBookPoint implements Serializable {

    @Builder.Default
    private String rol = "NORMAL";

    public static BooknBookPointBuilder booknBookPointBuilderFromMap(Map<String, String> map){
        return new BooknBookPointBuilder().rol(map.get("rol"));
    }

    public String toString() {
        return "BooknBookPoint.BooknBookPointBuilder(rol="+this.getRol()+")";
    }

}
