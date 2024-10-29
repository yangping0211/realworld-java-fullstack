package com.example.realwold.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class User {
    @Getter
    private UserId id;
    private Username username;
    private EmailAddress emailAddress;
    private Password password;

    public static User of(Username username, EmailAddress emailAddress, Password password) {
        return new User(UserId.newUserId(), username, emailAddress, password);
    }
}
