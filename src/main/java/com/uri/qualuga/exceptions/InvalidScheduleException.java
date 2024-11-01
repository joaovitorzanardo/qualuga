package com.uri.qualuga.exceptions;

public class InvalidScheduleException extends RuntimeException {
    public InvalidScheduleException(String message) {
        super("Horário Invalido! Motivo: " + message);
    }
}
