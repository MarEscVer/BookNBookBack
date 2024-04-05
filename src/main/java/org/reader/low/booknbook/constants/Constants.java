package org.reader.low.booknbook.constants;

public class Constants {


    public static final Integer MAX_VALUES = 35;

    //Si se incrementa este valor a más de 8, hay que añadir variables en el swich y en la base de datos.
    public static final Integer LONGITUD_URL = 7;

    private Constants(){
        throw new IllegalStateException("Constants class");
    }
}
