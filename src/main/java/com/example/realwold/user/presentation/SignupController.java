package com.example.realwold.user.presentation;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.realwold.user.UserCreatedEvent;
import com.example.realwold.user.application.DuplicateEmailException;
import com.example.realwold.user.application.DuplicateUsernameException;
import com.example.realwold.user.application.SignupCommand;
import com.example.realwold.user.application.SignupUsecase;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignupController {
    private final SignupUsecase signupUsecase;
    private final MessageSource messageSource;

    @GetMapping
    public String signupForm(Model model) {
        if (!model.containsAttribute("signupForm")) {
            model.addAttribute("signupForm", new SignupForm());
        }
        return "signup";
    }

    @PostMapping
    public String signup(@Validated SignupForm signupForm, BindingResult bindingResult,
            Locale locale, Model model) {
        if (bindingResult.hasErrors()) {
            return signupForm(model);
        }

        try {
            UserCreatedEvent event = signupUsecase.handle(new SignupCommand(
                    signupForm.getUsername(),
                    signupForm.getEmail(),
                    signupForm.getPassword()));
            return "redirect:/login";
        } catch (DuplicateUsernameException e) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "username",
                    messageSource.getMessage("DuplicateUsername", null, locale)));
            return signupForm(model);
        } catch (DuplicateEmailException e) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(), "email",
                    messageSource.getMessage("DuplicateEmail", null, locale)));
            return signupForm(model);
        }
    }
}
