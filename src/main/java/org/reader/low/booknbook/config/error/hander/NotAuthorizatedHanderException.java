package org.reader.low.booknbook.config.error.hander;

import org.apache.commons.lang3.StringUtils;
import org.reader.low.booknbook.config.error.HttpBaseException;
import org.springframework.http.HttpStatus;

public class NotAuthorizatedHanderException extends HttpBaseException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;
    private static final String DEFAULT_SHORT_DESCRIPTION = HttpStatus.UNAUTHORIZED.getReasonPhrase()+" exception";
    private static final String DEFAULT_LARGE_DESCRIPTION = HttpStatus.UNAUTHORIZED.getReasonPhrase();

    public NotAuthorizatedHanderException(String appError, String description) {
        super(appError, HTTP_STATUS, DEFAULT_SHORT_DESCRIPTION, StringUtils.defaultString(description,
                DEFAULT_LARGE_DESCRIPTION));
    }

    public NotAuthorizatedHanderException(String appError) {
        super(appError, null);
    }

    public NotAuthorizatedHanderException(String appError, HttpStatus httpCode, String shortDescription,
                                     String largeDescription) {
        super(appError, httpCode, shortDescription, largeDescription);
    }

    public NotAuthorizatedHanderException(String appError, HttpStatus httpCode, Integer personalizeCode,
                                     String shortDescription, String largeDescription) {
        super(appError, httpCode, personalizeCode, shortDescription, largeDescription);
    }

    public NotAuthorizatedHanderException(Exception e) {
        super(e);
    }
}
