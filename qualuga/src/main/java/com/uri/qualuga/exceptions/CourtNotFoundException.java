package com.uri.qualuga.exceptions;

public class CourtNotFoundException extends RuntimeException {
    public CourtNotFoundException() {
        super("Quadra não encontrada!");
    }
}
