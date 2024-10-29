package com.example.realwold.user;

import java.time.LocalDateTime;

public record UserCreatedEvent(String userId, LocalDateTime occurredAt) {
}
