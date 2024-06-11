package org.reader.low.booknbook.config.error;

import lombok.Getter;

import java.beans.ConstructorProperties;

/**
 * The type Generic exception.
 */
@Getter
public class GenericException extends AppException{

    /**
     * The App error.
     */
    private final String appError;

    /**
     * The Http code.
     */
    private final Integer httpCode;

    /**
     * The Short description.
     */
    private final String shortDescription;

    /**
     * The Large description.
     */
    private final String largeDescription;

    /**
     * Instantiates a new Generic exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    @ConstructorProperties({"appError", "httpCode", "shortDescription", "largeDescription"})
    public GenericException(String appError, Integer httpCode, String shortDescription, String largeDescription) {
        super(largeDescription);
        this.appError = appError;
        this.httpCode = httpCode;
        this.shortDescription = shortDescription;
        this.largeDescription = largeDescription;
    }

    /**
     * Instantiates a new Generic exception.
     *
     * @param appError the app error
     */
    public GenericException(String appError) {
        super("Generic Exception");
        this.appError = appError;
        this.httpCode = 500;
        this.shortDescription = "Generic Exception";
        this.largeDescription = "Generic Exception";
    }

    /**
     * Instantiates a new Generic exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public GenericException(String message, Throwable cause){
        super(message, cause);
        this.appError = cause != null ? cause.getClass().getName() : null;
        this.httpCode = 500;
        this.shortDescription = cause != null ? cause.getMessage() : "Generic Exception";
        this.largeDescription = cause != null ?  cause.getLocalizedMessage() : "Generic Exception";
    }

    /**
     * Instantiates a new Generic exception.
     *
     * @param e the e
     */
    public GenericException(Throwable e){
        super(e);
        this.appError = e.getClass().getName();
        this.httpCode = -1;
        this.shortDescription = e.getMessage();
        this.largeDescription = e.getLocalizedMessage();
    }
}
