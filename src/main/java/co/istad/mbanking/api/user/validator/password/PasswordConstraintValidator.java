package co.istad.mbanking.api.user.validator.password;

import co.istad.mbanking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.passay.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class PasswordConstraintValidator implements ConstraintValidator<Password , String> {
    private final UserMapper userMapper;
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // at least 8 characters
                new LengthRule(6, 15),
                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase,1),
                // at least  one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase,1),
                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit,1),
                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special,1),
                // no whitespace
                new WhitespaceRule()
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid()){
            return true;
        }
        List<String> messages = validator.getMessages(result);
        return false;
    }
}
