package org.reader.low.booknbook.config.error.hander;

import org.apache.commons.lang3.StringUtils;
import org.reader.low.booknbook.config.error.HttpBaseException;
import org.springframework.http.HttpStatus;

public class UnauthorizedHandlerException extends HttpBaseException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.FORBIDDEN;
    private static final String DEFAULT_SHORT_DESCRIPTION = HttpStatus.FORBIDDEN.getReasonPhrase()+" exception";
    private static final String DEFAULT_LARGE_DESCRIPTION = HttpStatus.FORBIDDEN.getReasonPhrase();

    public UnauthorizedHandlerException(String appError, String description) {
        super(appError, HTTP_STATUS, DEFAULT_SHORT_DESCRIPTION, StringUtils.defaultString(description,
                DEFAULT_LARGE_DESCRIPTION));
    }

    public UnauthorizedHandlerException(String appError) {
        super(appError);
    }

    public UnauthorizedHandlerException(String appError, HttpStatus httpCode, String shortDescription,
                                     String largeDescription) {
        super(appError, httpCode, shortDescription, largeDescription);
    }

    public UnauthorizedHandlerException(String appError, HttpStatus httpCode, Integer personalizeCode,
                                     String shortDescription, String largeDescription) {
        super(appError, httpCode, personalizeCode, shortDescription, largeDescription);
    }

    public UnauthorizedHandlerException(Exception e) {
        super(e);
    }
}
