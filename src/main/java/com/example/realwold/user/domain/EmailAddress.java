package com.example.realwold.user.domain;

public record EmailAddress(String value) {
    @Override
    public String toString() {
        return value;
    }
}
