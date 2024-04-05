package org.reader.low.booknbook.config;

import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.hander.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@Slf4j
@RestController
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(BadRequestHanderException.class)
    public ErrorModel handleBadRequestHanderException(BadRequestHanderException exception, ServletWebRequest webRequest){
        return ErrorModel.builder().appError(exception.getAppError()).httpCode(exception.getHttpCode())
                .shortDescription(exception.getShortDescription()).largeDescription(exception.getLargeDescription()).build();
    }

    @ExceptionHandler(InternalServerErrorHanderException.class)
    public ErrorModel handleInternalServerErrorHanderException(InternalServerErrorHanderException exception, ServletWebRequest webRequest){
        return ErrorModel.builder().appError(exception.getAppError()).httpCode(exception.getHttpCode())
                .shortDescription(exception.getShortDescription()).largeDescription(exception.getLargeDescription()).build();
    }

    @ExceptionHandler(NotAuthorizatedHanderException.class)
    public ErrorModel handleNotAuthorizatedHanderException(NotAuthorizatedHanderException exception, ServletWebRequest webRequest){
        return ErrorModel.builder().appError(exception.getAppError()).httpCode(exception.getHttpCode())
                .shortDescription(exception.getShortDescription()).largeDescription(exception.getLargeDescription()).build();
    }

    @ExceptionHandler(NotFoundHandlerException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorModel handleNotFoundHandlerException(NotFoundHandlerException exception, ServletWebRequest webRequest){
        log.warn("entra en el manejador NotFoundHandlerException");
        return ErrorModel.builder().appError(exception.getAppError()).httpCode(exception.getHttpCode())
                .shortDescription(exception.getShortDescription()).largeDescription(exception.getLargeDescription()).build();
    }

    @ExceptionHandler(UnauthorizedHandlerException.class)
    public ErrorModel handleUnauthorizedHandlerException(UnauthorizedHandlerException exception, ServletWebRequest webRequest){
        return ErrorModel.builder().appError(exception.getAppError()).httpCode(exception.getHttpCode())
                .shortDescription(exception.getShortDescription()).largeDescription(exception.getLargeDescription()).build();
    }

}
