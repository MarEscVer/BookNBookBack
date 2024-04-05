package org.reader.low.booknbook.config.error.hander;

import org.apache.commons.lang3.StringUtils;
import org.reader.low.booknbook.config.error.HttpBaseException;
import org.springframework.http.HttpStatus;

public class NotFoundHandlerException extends HttpBaseException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;
    private static final String DEFAULT_SHORT_DESCRIPTION = HttpStatus.NOT_FOUND.getReasonPhrase()+" exception";
    private static final String DEFAULT_LARGE_DESCRIPTION = HttpStatus.NOT_FOUND.getReasonPhrase();

    public NotFoundHandlerException(String appError, String description) {
        super(appError, HTTP_STATUS, DEFAULT_SHORT_DESCRIPTION, StringUtils.defaultString(description,
                DEFAULT_LARGE_DESCRIPTION));
    }

    public NotFoundHandlerException(String appError) {
        super(appError, null);
    }

    public NotFoundHandlerException(String appError, HttpStatus httpCode, String shortDescription,
                                    String largeDescription) {
        super(appError, httpCode, shortDescription, largeDescription);
    }

    public NotFoundHandlerException(String appError, HttpStatus httpCode, Integer personalizeCode,
                                    String shortDescription, String largeDescription) {
        super(appError, httpCode, personalizeCode, shortDescription, largeDescription);
    }

    public NotFoundHandlerException(Exception e) {
        super(e);
    }
}
