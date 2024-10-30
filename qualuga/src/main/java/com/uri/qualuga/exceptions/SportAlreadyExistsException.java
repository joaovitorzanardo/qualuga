package com.uri.qualuga.exceptions;

public class SportAlreadyExistsException extends RuntimeException{
    public SportAlreadyExistsException() {
        super("Esse esporte já foi cadastrado no sistema!");
    }
}
