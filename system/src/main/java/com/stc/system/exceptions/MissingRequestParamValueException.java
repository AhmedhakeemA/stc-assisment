package com.stc.system.exceptions;

/**
 * @author AhmedHakeem
 */
public class MissingRequestParamValueException extends RuntimeException{

    public MissingRequestParamValueException() {
        super();
    }
    public MissingRequestParamValueException(String message, Throwable cause) {
        super(message, cause);
    }
    public MissingRequestParamValueException(String message) {
        super(message);
    }
    public MissingRequestParamValueException(Throwable cause) {
        super(cause);
    }
}
