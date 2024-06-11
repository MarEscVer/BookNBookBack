package org.reader.low.booknbook.config.error.hander;

import org.apache.commons.lang3.StringUtils;
import org.reader.low.booknbook.config.error.HttpBaseException;
import org.springframework.http.HttpStatus;

/**
 * The type Bad request hander exception.
 */
public class BadRequestHanderException extends HttpBaseException {
    /**
     * The Http status.
     */
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;
    /**
     * The Default short description.
     */
    private static final String DEFAULT_SHORT_DESCRIPTION = HttpStatus.BAD_REQUEST.getReasonPhrase()+" exception";
    /**
     * The Default large description.
     */
    private static final String DEFAULT_LARGE_DESCRIPTION = HttpStatus.BAD_REQUEST.getReasonPhrase();

    /**
     * Instantiates a new Bad request hander exception.
     *
     * @param appError    the app error
     * @param description the description
     */
    public BadRequestHanderException(String appError, String description) {
        super(appError, HTTP_STATUS, DEFAULT_SHORT_DESCRIPTION, StringUtils.defaultString(description,
                DEFAULT_LARGE_DESCRIPTION));
    }

    /**
     * Instantiates a new Bad request hander exception.
     *
     * @param appError the app error
     */
    public BadRequestHanderException(String appError) {
        super(appError, null);
    }

    /**
     * Instantiates a new Bad request hander exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    public BadRequestHanderException(String appError, HttpStatus httpCode, String shortDescription,
                                    String largeDescription) {
        super(appError, httpCode, shortDescription, largeDescription);
    }

    /**
     * Instantiates a new Bad request hander exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param personalizeCode  the personalize code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    public BadRequestHanderException(String appError, HttpStatus httpCode, Integer personalizeCode,
                                    String shortDescription, String largeDescription) {
        super(appError, httpCode, personalizeCode, shortDescription, largeDescription);
    }

    /**
     * Instantiates a new Bad request hander exception.
     *
     * @param e the e
     */
    public BadRequestHanderException(Exception e) {
        super(e);
    }

}
