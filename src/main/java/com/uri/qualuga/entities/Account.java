package com.uri.qualuga.entities;

public interface Account {

    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    String getEmail();
    void setEmail(String email);

    String getPassword();
    void setPassword(String password);

}
