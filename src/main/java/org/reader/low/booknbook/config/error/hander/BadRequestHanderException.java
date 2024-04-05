package org.reader.low.booknbook.config.error.hander;

import org.apache.commons.lang3.StringUtils;
import org.reader.low.booknbook.config.error.HttpBaseException;
import org.springframework.http.HttpStatus;

public class BadRequestHanderException extends HttpBaseException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;
    private static final String DEFAULT_SHORT_DESCRIPTION = HttpStatus.BAD_REQUEST.getReasonPhrase()+" exception";
    private static final String DEFAULT_LARGE_DESCRIPTION = HttpStatus.BAD_REQUEST.getReasonPhrase();

    public BadRequestHanderException(String appError, String description) {
        super(appError, HTTP_STATUS, DEFAULT_SHORT_DESCRIPTION, StringUtils.defaultString(description,
                DEFAULT_LARGE_DESCRIPTION));
    }

    public BadRequestHanderException(String appError) {
        super(appError, null);
    }

    public BadRequestHanderException(String appError, HttpStatus httpCode, String shortDescription,
                                    String largeDescription) {
        super(appError, httpCode, shortDescription, largeDescription);
    }

    public BadRequestHanderException(String appError, HttpStatus httpCode, Integer personalizeCode,
                                    String shortDescription, String largeDescription) {
        super(appError, httpCode, personalizeCode, shortDescription, largeDescription);
    }

    public BadRequestHanderException(Exception e) {
        super(e);
    }

}
