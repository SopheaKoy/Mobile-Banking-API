package co.istad.mbanking.api.auth.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDto(
        @NotBlank(message = "email is required")
                @Email
                          String email,
                          @NotBlank(message = "password is required")
                          String password,
                          @NotBlank(message = "confirmedPassword is required..!")
                          String confirmedPassword) {
}
