package co.istad.mbanking.api.user.validator.password;

import co.istad.mbanking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordConstraintValidator implements ConstraintValidator<Password , String> {
    private final UserMapper userMapper;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
