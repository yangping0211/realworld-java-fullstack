package com.example.realwold.user.application;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.realwold.user.domain.EmailAddress;
import com.example.realwold.user.domain.User;
import com.example.realwold.user.domain.UserRepository;
import com.example.realwold.user.domain.Username;

@ExtendWith(MockitoExtension.class)
class SignupUsecaseTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    SignupUsecase signupUsecase;

    private static final String TEST_USERNAME = "test";
    private static final String TEST_EMAIL = "test@example.com";
    private static final String TEST_PASSWORD = "password";

    @Test
    void registerUserSuccess() {
        var command = new SignupCommand(TEST_USERNAME, TEST_EMAIL, TEST_PASSWORD);
        var event = signupUsecase.handle(command);
        verify(userRepository, times(1)).save(any(User.class));
        verify(applicationEventPublisher, times(1)).publishEvent(event);
    }

    @Test
    void registerUserWithAlreadyExistsUsernameFail() {
        var command = new SignupCommand(TEST_USERNAME, TEST_EMAIL, TEST_PASSWORD);
        var username = new Username(TEST_USERNAME);
        when(userRepository.isDuplicateUsername(username)).thenReturn(true);

        assertThrows(DuplicateUsernameException.class, () -> signupUsecase.handle(command));
    }

    @Test
    void registerUserWithAlreadyExistsEmailFail() {
        var command = new SignupCommand(TEST_USERNAME, TEST_EMAIL, TEST_PASSWORD);
        var emailAddress = new EmailAddress(TEST_EMAIL);
        when(userRepository.isDuplicateEmail(emailAddress)).thenReturn(true);

        assertThrows(DuplicateEmailException.class, () -> signupUsecase.handle(command));
    }
}
