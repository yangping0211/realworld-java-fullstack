package com.example.realwold.user.domain;

import java.util.UUID;

public record UserId(UUID value) {
    public static UserId newUserId() {
        return new UserId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
