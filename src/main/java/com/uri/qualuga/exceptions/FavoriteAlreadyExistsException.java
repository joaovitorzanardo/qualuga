package com.uri.qualuga.exceptions;

public class FavoriteAlreadyExistsException extends RuntimeException {
    public FavoriteAlreadyExistsException() {
        super("Esse estabelecimento já está nos seus favoritos!");
    }
}
