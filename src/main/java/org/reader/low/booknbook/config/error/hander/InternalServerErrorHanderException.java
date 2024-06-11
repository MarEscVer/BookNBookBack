package org.reader.low.booknbook.config.error.hander;

import org.apache.commons.lang3.StringUtils;
import org.reader.low.booknbook.config.error.HttpBaseException;
import org.springframework.http.HttpStatus;

/**
 * The type Internal server error hander exception.
 */
public class InternalServerErrorHanderException extends HttpBaseException {
    /**
     * The Http status.
     */
    private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    /**
     * The Default short description.
     */
    private static final String DEFAULT_SHORT_DESCRIPTION = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+" exception";
    /**
     * The Default large description.
     */
    private static final String DEFAULT_LARGE_DESCRIPTION = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

    /**
     * Instantiates a new Internal server error hander exception.
     *
     * @param appError    the app error
     * @param description the description
     */
    public InternalServerErrorHanderException(String appError, String description) {
        super(appError, HTTP_STATUS, DEFAULT_SHORT_DESCRIPTION, StringUtils.defaultString(description,
                DEFAULT_LARGE_DESCRIPTION));
    }

    /**
     * Instantiates a new Internal server error hander exception.
     *
     * @param appError the app error
     */
    public InternalServerErrorHanderException(String appError) {
        super(appError, null);
    }

    /**
     * Instantiates a new Internal server error hander exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    public InternalServerErrorHanderException(String appError, HttpStatus httpCode, String shortDescription,
                                     String largeDescription) {
        super(appError, httpCode, shortDescription, largeDescription);
    }

    /**
     * Instantiates a new Internal server error hander exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param personalizeCode  the personalize code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    public InternalServerErrorHanderException(String appError, HttpStatus httpCode, Integer personalizeCode,
                                     String shortDescription, String largeDescription) {
        super(appError, httpCode, personalizeCode, shortDescription, largeDescription);
    }

    /**
     * Instantiates a new Internal server error hander exception.
     *
     * @param e the e
     */
    public InternalServerErrorHanderException(Exception e) {
        super(e);
    }
}
