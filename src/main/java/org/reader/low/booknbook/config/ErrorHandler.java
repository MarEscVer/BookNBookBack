package org.reader.low.booknbook.config;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.reader.low.booknbook.config.error.HttpBaseException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The type Error handler.
 */
@Slf4j
@RestControllerAdvice
@Order
public class ErrorHandler {

    /**
     * Handle exception response entity.
     *
     * @param exception           the exception
     * @param servletRequest      the servlet request
     * @param httpServletResponse the http servlet response
     * @return the response entity
     */
    @ExceptionHandler(HttpBaseException.class)
    public ResponseEntity<ErrorModel> handleException(HttpBaseException exception, HttpServletRequest servletRequest, HttpServletResponse httpServletResponse){
        return new ResponseEntity<>(mapToErrorModel(exception), exception.getStatus());
    }

    /**
     * Handle exception response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorModel> handleException(RuntimeException exception){
        return new ResponseEntity<>(mapToErrorModel(exception), HttpStatus.BAD_REQUEST);
    }

    ErrorModel mapToErrorModel(HttpBaseException exception){
        return ErrorModel.builder().appError(exception.getAppError()).httpCode(exception.getHttpCode())
                .shortDescription(exception.getShortDescription()).largeDescription(exception.getLargeDescription()).build();
    }

    ErrorModel mapToErrorModel(RuntimeException exception){
        return ErrorModel.builder().appError("error_mysql").httpCode(400)
                .shortDescription(exception.getMessage()).largeDescription(exception.getLocalizedMessage()).build();
    }

    ErrorModel mapToErrorModel(JwtException exception){
        return ErrorModel.builder().appError("token_validation").httpCode(HttpStatus.UNAUTHORIZED.value())
                .shortDescription("Error al procesar el token").largeDescription(exception.getLocalizedMessage()).build();
    }
}
