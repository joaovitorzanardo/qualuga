package com.uri.qualuga.exceptions;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException() {
        super("Horário informado não encontrado!");
    }
}
