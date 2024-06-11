package org.reader.low.booknbook.config.error.hander;

import org.apache.commons.lang3.StringUtils;
import org.reader.low.booknbook.config.error.HttpBaseException;
import org.springframework.http.HttpStatus;

/**
 * The type Not found handler exception.
 */
public class NotFoundHandlerException extends HttpBaseException {

    /**
     * The Http status.
     */
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;
    /**
     * The Default short description.
     */
    private static final String DEFAULT_SHORT_DESCRIPTION = HttpStatus.NOT_FOUND.getReasonPhrase()+" exception";
    /**
     * The Default large description.
     */
    private static final String DEFAULT_LARGE_DESCRIPTION = HttpStatus.NOT_FOUND.getReasonPhrase();

    /**
     * Instantiates a new Not found handler exception.
     *
     * @param appError    the app error
     * @param description the description
     */
    public NotFoundHandlerException(String appError, String description) {
        super(appError, HTTP_STATUS, DEFAULT_SHORT_DESCRIPTION, StringUtils.defaultString(description,
                DEFAULT_LARGE_DESCRIPTION));
    }

    /**
     * Instantiates a new Not found handler exception.
     *
     * @param appError the app error
     */
    public NotFoundHandlerException(String appError) {
        super(appError, null);
    }

    /**
     * Instantiates a new Not found handler exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    public NotFoundHandlerException(String appError, HttpStatus httpCode, String shortDescription,
                                    String largeDescription) {
        super(appError, httpCode, shortDescription, largeDescription);
    }

    /**
     * Instantiates a new Not found handler exception.
     *
     * @param appError         the app error
     * @param httpCode         the http code
     * @param personalizeCode  the personalize code
     * @param shortDescription the short description
     * @param largeDescription the large description
     */
    public NotFoundHandlerException(String appError, HttpStatus httpCode, Integer personalizeCode,
                                    String shortDescription, String largeDescription) {
        super(appError, httpCode, personalizeCode, shortDescription, largeDescription);
    }

    /**
     * Instantiates a new Not found handler exception.
     *
     * @param e the e
     */
    public NotFoundHandlerException(Exception e) {
        super(e);
    }
}
