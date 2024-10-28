package com.example.realwold.user.infrastructure;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.example.realwold.user.domain.User;
import com.example.realwold.user.domain.UserId;
import com.example.realwold.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserMybatisRepository implements UserRepository {
    private final UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public Optional<User> load(UserId id) {
        return userMapper.selectByUserId(id);
    }
}
