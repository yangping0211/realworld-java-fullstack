package com.example.realwold.user.infrastructure;

import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import com.example.realwold.user.domain.EmailAddress;
import com.example.realwold.user.domain.User;
import com.example.realwold.user.domain.UserId;
import com.example.realwold.user.domain.Username;

@Mapper
public interface UserMapper {
    @Insert("""
            INSERT INTO users(id, username, email_address, password) VALUES(
                #{id.value},
                #{username.value},
                #{emailAddress.value},
                #{password.value}
            )
            """)
    public int insert(User user);

    @Select("""
            SELECT
                id,
                username,
                email_address,
                password
            FROM users
            WHERE id = #{value}
            """)
    @ResultMap("com.example.realwold.user.infrastructure.UserMapper.userMap")
    Optional<User> selectByUserId(UserId id);

    @Select("""
            SELECT
                id,
                username,
                email_address,
                password
            FROM users
            WHERE username = #{value}
            """)
    @ResultMap("com.example.realwold.user.infrastructure.UserMapper.userMap")
    Optional<User> selectByUsername(Username username);

    @Select("""
            SELECT
                id,
                username,
                email_address,
                password
            FROM users
            WHERE email_address = #{value}
            """)
    @ResultMap("com.example.realwold.user.infrastructure.UserMapper.userMap")
    Optional<User> selectByEmail(EmailAddress emailAddress);
}
