package com.example.realwold.user.domain;

public record Username(String value) {
    @Override
    public String toString() {
        return value;
    }
}
