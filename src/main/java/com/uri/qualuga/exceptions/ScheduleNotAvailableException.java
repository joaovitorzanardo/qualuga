package com.uri.qualuga.exceptions;

public class ScheduleNotAvailableException extends RuntimeException {
    public ScheduleNotAvailableException() {
        super("Esse horário não está mais disponível!");
    }
}
