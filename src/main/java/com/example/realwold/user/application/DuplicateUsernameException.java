package com.example.realwold.user.application;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException(String s) {
        super(s);
    }
}
