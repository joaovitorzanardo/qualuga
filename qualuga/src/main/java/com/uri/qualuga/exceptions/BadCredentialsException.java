package com.uri.qualuga.exceptions;

public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException() {
        super("Email ou senha incorreto!");
    }
}
