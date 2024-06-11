package org.reader.low.booknbook.config.error;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The type Http base exception.
 */
@EqualsAndHashCode(callSuper = true)
public class HttpBaseException extends GenericException {

    /**
     * The Status.
     */
    @Getter
    private final HttpStatus status;

    /**
     * Instantiates a new Http base exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    public HttpBaseException(String appError, HttpStatus httpCode, String shortDescription, String largeDescription) {
        super(appError, httpCode.value(), shortDescription, largeDescription);
        this.status = httpCode;
    }

    /**
     * Instantiates a new Http base exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param personalizeCode  the personalize code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    public HttpBaseException(String appError, HttpStatus httpCode, Integer personalizeCode, String shortDescription, String largeDescription) {
        super(appError, personalizeCode, shortDescription, largeDescription);
        this.status = httpCode;
    }

    /**
     * Instantiates a new Http base exception.
     *
     * @param appError the app error
     */
    public HttpBaseException(String appError){
        super(appError);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Instantiates a new Http base exception.
     *
     * @param message the message
     * @param e       the e
     */
    public HttpBaseException(String message, Throwable e){
        super(message, e);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Instantiates a new Http base exception.
     *
     * @param e the e
     */
    public HttpBaseException(Exception e){
        super(e);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
