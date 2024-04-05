package org.reader.low.booknbook.config.error;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class GenericException extends AppException{

    private final String appError;

    private final Integer httpCode;

    private final String shortDescription;

    private final String largeDescription;

    @ConstructorProperties({"appError", "httpCode", "shortDescription", "largeDescription"})
    public GenericException(String appError, Integer httpCode, String shortDescription, String largeDescription) {
        super(largeDescription);
        this.appError = appError;
        this.httpCode = httpCode;
        this.shortDescription = shortDescription;
        this.largeDescription = largeDescription;
    }

    public GenericException(String message, Throwable cause){
        super(message, cause);
        this.appError = cause.getClass().getName();
        this.httpCode = -1;
        this.shortDescription = cause.getMessage();
        this.largeDescription = cause.getLocalizedMessage();
    }

    public GenericException(Throwable e){
        super(e);
        this.appError = e.getClass().getName();
        this.httpCode = -1;
        this.shortDescription = e.getMessage();
        this.largeDescription = e.getLocalizedMessage();
    }
}
