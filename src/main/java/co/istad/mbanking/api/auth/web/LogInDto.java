package co.istad.mbanking.api.auth.web;

import jakarta.validation.constraints.NotBlank;

public record LogInDto(@NotBlank String email, @NotBlank String password) {

}
