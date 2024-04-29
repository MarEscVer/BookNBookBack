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

@Slf4j
//@RestController
//@ControllerAdvice
@RestControllerAdvice
@Order
public class ErrorHandler {

    @ExceptionHandler(HttpBaseException.class)
    public ResponseEntity<ErrorModel> handleException(HttpBaseException exception, HttpServletRequest servletRequest, HttpServletResponse httpServletResponse){
        return new ResponseEntity<>(mapToErrorModel(exception), exception.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorModel> handleException(RuntimeException exception){
        return new ResponseEntity<>(mapToErrorModel(exception), HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(StopPooledThreadException.class)
    public ResponseEntity<ErrorModel> handleExceptionFilter(StopPooledThreadException exception){
        return new ResponseEntity<>(mapToErrorModel(exception),HttpStatus.BAD_REQUEST);
    }*/

    private ErrorModel mapToErrorModel(HttpBaseException exception){
        return ErrorModel.builder().appError(exception.getAppError()).httpCode(exception.getHttpCode())
                .shortDescription(exception.getShortDescription()).largeDescription(exception.getLargeDescription()).build();
    }

    private ErrorModel mapToErrorModel(RuntimeException exception){
        return ErrorModel.builder().appError("error_mysql").httpCode(400)
                .shortDescription(exception.getMessage()).largeDescription(exception.getLocalizedMessage()).build();
    }

    private ErrorModel mapToErrorModel(JwtException exception){
        return ErrorModel.builder().appError("token_validation").httpCode(HttpStatus.UNAUTHORIZED.value())
                .shortDescription("Error al procesar el token").largeDescription(exception.getLocalizedMessage()).build();
    }
}
