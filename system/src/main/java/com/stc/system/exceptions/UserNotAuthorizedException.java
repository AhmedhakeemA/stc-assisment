package com.stc.system.exceptions;

/**
 * @author AhmedHakeem
 */
public class UserNotAuthorizedException extends RuntimeException{

    public UserNotAuthorizedException() {
        super();
    }
    public UserNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserNotAuthorizedException(String message) {
        super(message);
    }
    public UserNotAuthorizedException(Throwable cause) {
        super(cause);
    }
}
