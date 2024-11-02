package com.example.realwold.user.presentation;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupForm {
    @NotBlank
    @Length(max = 100)
    private String username;
    @NotBlank
    @Email
    private String email;
    @Length(min = 8, max = 100)
    private String password;
}
