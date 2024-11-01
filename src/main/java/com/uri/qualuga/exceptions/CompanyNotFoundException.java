package com.uri.qualuga.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException() {
        super("Empresa não encontrada!");
    }
}
