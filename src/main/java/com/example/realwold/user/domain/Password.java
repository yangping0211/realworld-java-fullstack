package com.example.realwold.user.domain;

public record Password(String value) {
    @Override
    public String toString() {
        return value;
    }
}
