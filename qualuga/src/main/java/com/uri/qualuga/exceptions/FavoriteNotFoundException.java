package com.uri.qualuga.exceptions;

public class FavoriteNotFoundException extends RuntimeException {
    public FavoriteNotFoundException() {
        super("Esse estabelecimento não está nos seus favoritos!");
    }
}
