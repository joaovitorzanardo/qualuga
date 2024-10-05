package com.uri.qualuga;

import com.uri.qualuga.dtos.ErrorResponse;
import com.uri.qualuga.exceptions.EmailAlreadyExistsException;
import com.uri.qualuga.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message(ex.getMessage()).build();
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.GONE)
                .message(ex.getMessage()).build();
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }


}
