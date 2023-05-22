package co.istad.mbanking.api.auth.web;

import co.istad.mbanking.api.user.validator.email.EmailUnique;
import co.istad.mbanking.api.user.validator.password.Password;
import co.istad.mbanking.api.user.validator.password.PasswordMatch;
import co.istad.mbanking.api.user.validator.role.RoleIdConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@PasswordMatch(password = "password", confirmPassword = "confirmedPassword" , message = "your is password is not the same....!")
public record RegisterDto(
        @NotBlank(message = "email is required")
        @EmailUnique
        @Email
        String email,
        @NotBlank(message = "password is required")
        @Password
        String password,
        @NotBlank(message = "confirmedPassword is required..!")
        @Password
        String confirmedPassword,

        @NotNull(message = "Roles are required")
        @RoleIdConstraint
        List<Integer> roleIds) {
}
