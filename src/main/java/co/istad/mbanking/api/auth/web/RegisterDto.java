package co.istad.mbanking.api.auth.web;

import co.istad.mbanking.api.user.validator.email.EmailUnique;
import co.istad.mbanking.api.user.validator.role.RoleIdConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterDto(
        @NotBlank(message = "email is required")
        @EmailUnique
        @Email
        String email,
        @NotBlank(message = "password is required")
        String password,
        @NotBlank(message = "confirmedPassword is required..!")
        String confirmedPassword,

        @NotNull(message = "Roles are required")
        @RoleIdConstraint
        List<Integer> roleIds) {
}
