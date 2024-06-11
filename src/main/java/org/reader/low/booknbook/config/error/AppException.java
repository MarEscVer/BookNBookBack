package org.reader.low.booknbook.config.error;

/**
 * The type App exception.
 */
public class AppException extends RuntimeException{

    /**
     * Instantiates a new App exception.
     */
    public AppException(){
        super();
    }

    /**
     * Instantiates a new App exception.
     *
     * @param message the message
     */
    public AppException(String message){super(message);}

    /**
     * Instantiates a new App exception.
     *
     * @param cause the cause
     */
    public AppException(Throwable cause){
        super(cause);
    }

    /**
     * Instantiates a new App exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public AppException(String message, Throwable cause){
        super(message, cause);
    }
}
