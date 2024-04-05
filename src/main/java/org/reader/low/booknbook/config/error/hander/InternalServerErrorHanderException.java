package org.reader.low.booknbook.config.error.hander;

import org.apache.commons.lang3.StringUtils;
import org.reader.low.booknbook.config.error.HttpBaseException;
import org.springframework.http.HttpStatus;

public class InternalServerErrorHanderException extends HttpBaseException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String DEFAULT_SHORT_DESCRIPTION = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+" exception";
    private static final String DEFAULT_LARGE_DESCRIPTION = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

    public InternalServerErrorHanderException(String appError, String description) {
        super(appError, HTTP_STATUS, DEFAULT_SHORT_DESCRIPTION, StringUtils.defaultString(description,
                DEFAULT_LARGE_DESCRIPTION));
    }

    public InternalServerErrorHanderException(String appError) {
        super(appError, null);
    }

    public InternalServerErrorHanderException(String appError, HttpStatus httpCode, String shortDescription,
                                     String largeDescription) {
        super(appError, httpCode, shortDescription, largeDescription);
    }

    public InternalServerErrorHanderException(String appError, HttpStatus httpCode, Integer personalizeCode,
                                     String shortDescription, String largeDescription) {
        super(appError, httpCode, personalizeCode, shortDescription, largeDescription);
    }

    public InternalServerErrorHanderException(Exception e) {
        super(e);
    }
}
