package org.reader.low.booknbook.config.error.hander;

import org.apache.commons.lang3.StringUtils;
import org.reader.low.booknbook.config.error.HttpBaseException;
import org.springframework.http.HttpStatus;

/**
 * The type Unauthorized handler exception.
 */
public class UnauthorizedHandlerException extends HttpBaseException {
    /**
     * The Http status.
     */
    private static final HttpStatus HTTP_STATUS = HttpStatus.FORBIDDEN;
    /**
     * The Default short description.
     */
    private static final String DEFAULT_SHORT_DESCRIPTION = HttpStatus.FORBIDDEN.getReasonPhrase()+" exception";
    /**
     * The Default large description.
     */
    private static final String DEFAULT_LARGE_DESCRIPTION = HttpStatus.FORBIDDEN.getReasonPhrase();

    /**
     * Instantiates a new Unauthorized handler exception.
     *
     * @param appError    the app error
     * @param description the description
     */
    public UnauthorizedHandlerException(String appError, String description) {
        super(appError, HTTP_STATUS, DEFAULT_SHORT_DESCRIPTION, StringUtils.defaultString(description,
                DEFAULT_LARGE_DESCRIPTION));
    }

    /**
     * Instantiates a new Unauthorized handler exception.
     *
     * @param appError the app error
     */
    public UnauthorizedHandlerException(String appError) {
        super(appError);
    }

    /**
     * Instantiates a new Unauthorized handler exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    public UnauthorizedHandlerException(String appError, HttpStatus httpCode, String shortDescription,
                                     String largeDescription) {
        super(appError, httpCode, shortDescription, largeDescription);
    }

    /**
     * Instantiates a new Unauthorized handler exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param personalizeCode  the personalize code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    public UnauthorizedHandlerException(String appError, HttpStatus httpCode, Integer personalizeCode,
                                     String shortDescription, String largeDescription) {
        super(appError, httpCode, personalizeCode, shortDescription, largeDescription);
    }

    /**
     * Instantiates a new Unauthorized handler exception.
     *
     * @param e the e
     */
    public UnauthorizedHandlerException(Exception e) {
        super(e);
    }
}
