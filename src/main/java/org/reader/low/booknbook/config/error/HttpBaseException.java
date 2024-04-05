package org.reader.low.booknbook.config.error;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
public class HttpBaseException extends GenericException {

    @Getter
    private final HttpStatus status;
    public HttpBaseException(String appError, HttpStatus httpCode, String shortDescription, String largeDescription) {
        super(appError, httpCode.value(), shortDescription, largeDescription);
        this.status = httpCode;
    }

    public HttpBaseException(String appError, HttpStatus httpCode, Integer personalizeCode, String shortDescription, String largeDescription) {
        super(appError, personalizeCode, shortDescription, largeDescription);
        this.status = httpCode;
    }

    public HttpBaseException(String message, Throwable e){
        super(message, e);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    public HttpBaseException(Exception e){
        super(e);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
