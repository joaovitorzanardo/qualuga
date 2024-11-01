package com.uri.qualuga.exceptions;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException() {
        super("Data não pode ser menor que hoje!");
    }
}
