package com.uri.qualuga.exceptions;

public class CourtNumberAlreadyExistsException extends RuntimeException {
    public CourtNumberAlreadyExistsException() {
        super("Já existe uma quadra com esse número!");
    }
}
