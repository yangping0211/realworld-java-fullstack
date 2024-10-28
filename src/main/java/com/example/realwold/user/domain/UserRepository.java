package com.example.realwold.user.domain;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> load(UserId id);
}
