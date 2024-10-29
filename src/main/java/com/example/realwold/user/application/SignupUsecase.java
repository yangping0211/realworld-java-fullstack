package com.example.realwold.user.application;

import java.time.LocalDateTime;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.realwold.user.UserCreatedEvent;
import com.example.realwold.user.domain.EmailAddress;
import com.example.realwold.user.domain.Password;
import com.example.realwold.user.domain.User;
import com.example.realwold.user.domain.UserRepository;
import com.example.realwold.user.domain.Username;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupUsecase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserCreatedEvent handle(SignupCommand command) {
        Username username = new Username(command.username());
        if (userRepository.isDuplicateUsername(username)) {
            throw new DuplicateUsernameException(command.username());
        }

        EmailAddress emailAddress = new EmailAddress(command.emailAddress());
        if (userRepository.isDuplicateEmail(emailAddress)) {
            throw new DuplicateEmailException(command.emailAddress());
        }

        Password password = new Password(passwordEncoder.encode(command.password()));
        User user = User.of(username, emailAddress, password);

        userRepository.save(user);

        UserCreatedEvent event =
                new UserCreatedEvent(user.getId().value().toString(), LocalDateTime.now());
        applicationEventPublisher.publishEvent(event);
        return event;
    }
}
