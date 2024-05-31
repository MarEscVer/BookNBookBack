package org.reader.low.booknbook.constants;

public class ApiConstants {

    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String BAD_REQUEST_400 = "400";
    public static final String UNAUTH = "UNAUTHORIZED";
    public static final String UNAUTH_401 = "401";
    public static final String AUTHORIZATION = "forbidden_darwin";
    public static final String AUTHORIZATION_403 = "403";
    public static final String NOTFOUND = "Not found";
    public static final String NOTFOUND_404 = "404";
    public static final String INSERE = "Internal server error";
    public static final String INSERE_500 = "500";
    public static final String SUCCESSFULL = "successful operation";
    public static final String SUCCESSFULL_200 = "200";
    public static final String OK = "OK";
    public static final String JSON_RESPONSE = "application/json";
    public static final String ERROR_MODEL = "#/components/schemas/errorModel";
    public static final String ERROR_CODE_CHARACTER = "character_not_valid";
    public static final String TAG_USUARIO = "Usuario";
    public static final String TAG_DENUNCIA = "Denuncia";
    public static final String TAG_AUTOR = "Autor";
    public static final String TAG_GRUPO = "Grupo";
    public static final String TAG_PUBLICO = "Metodos de uso Público";
    public static final String TAG_ADMIN = "Administrador";
    public static final String TAG_COMBO = "Combos";
    public static final String TAG_LIBRO = "Libro";

    private ApiConstants(){
        throw new IllegalStateException("ApiConstants class");
    }
}
