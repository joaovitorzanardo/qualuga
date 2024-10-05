package com.uri.qualuga.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException() {
        super("Já existe uma conta cadastrada com esse email!");
    }
}
