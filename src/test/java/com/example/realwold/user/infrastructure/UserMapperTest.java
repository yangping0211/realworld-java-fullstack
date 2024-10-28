package com.example.realwold.user.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.example.realwold.user.domain.EmailAddress;
import com.example.realwold.user.domain.Password;
import com.example.realwold.user.domain.User;
import com.example.realwold.user.domain.UserId;
import com.example.realwold.user.domain.Username;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void testInsert() {
        var id = UserId.newUserId();
        var username = new Username("username");
        var emailAddress = new EmailAddress("test@example.com");
        var password = new Password("password");
        var user = new User(id, username, emailAddress, password);
        int insertCount = userMapper.insert(user);

        assertThat(insertCount).isEqualTo(1);
    }

    @Test
    @Sql("./test-users.sql")
    void testSelectByUserId() {
        var id = new UserId(UUID.fromString("0f35dc8f-c192-40b5-9357-d3abce144eaf"));
        var user = userMapper.selectByUserId(id);

        assertThat(user.isPresent()).isTrue();
    }
}
