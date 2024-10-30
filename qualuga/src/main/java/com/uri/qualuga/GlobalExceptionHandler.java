package com.uri.qualuga;

import com.uri.qualuga.dtos.ErrorResponse;
import com.uri.qualuga.exceptions.*;
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

    @ExceptionHandler(SportNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSportNotFoundException(SportNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.GONE)
                .message(ex.getMessage()).build();
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(CourtNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourtNotFoundException(CourtNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.GONE)
                .message(ex.getMessage()).build();
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCompanyNotFoundException(CompanyNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.GONE)
                .message(ex.getMessage()).build();
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(SportAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleSportAlreadyExistsException(SportAlreadyExistsException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message(ex.getMessage()).build();
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(CourtNumberAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCourtNumberAlreadyExistsException(CourtNumberAlreadyExistsException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message(ex.getMessage()).build();
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDateException(InvalidDateException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatus(HttpStatus.GONE)
                .message(ex.getMessage()).build();
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }

}
