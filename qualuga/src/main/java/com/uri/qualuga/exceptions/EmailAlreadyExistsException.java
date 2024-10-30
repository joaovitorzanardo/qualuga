package com.uri.qualuga.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super("Já existe uma conta cadastrada com o email " + email + "!");
    }
}
