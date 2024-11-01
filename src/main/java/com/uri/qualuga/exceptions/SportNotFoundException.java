package com.uri.qualuga.exceptions;

public class SportNotFoundException extends RuntimeException {
    public SportNotFoundException() {
        super("Esporte não encontrado!");
    }
}
