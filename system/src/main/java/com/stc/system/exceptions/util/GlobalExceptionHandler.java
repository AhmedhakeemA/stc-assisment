package com.stc.system.exceptions.util;

import com.stc.system.exceptions.MissingRequestParamValueException;
import com.stc.system.exceptions.RequestValidationException;
import com.stc.system.exceptions.ResourceNotFoundException;
import com.stc.system.exceptions.UserNotAuthorizedException;
import com.stc.system.exceptions.dto.ExceptionDetailsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author AhmedHakeem
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class, MissingRequestParamValueException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(Exception exception) {
        return new ResponseEntity<>(new ExceptionDetailsResponse(HttpStatus.NOT_FOUND,exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserNotAuthorizedException.class})
    public ResponseEntity<Object> handleUnAuthorizedExceptions(UserNotAuthorizedException exception) {
        return new ResponseEntity<>(new ExceptionDetailsResponse(HttpStatus.UNAUTHORIZED,exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {RequestValidationException.class})
    public ResponseEntity<Object> handleUnValidationExceptions(RequestValidationException exception) {
        return new ResponseEntity<>(new ExceptionDetailsResponse(HttpStatus.BAD_REQUEST,exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
