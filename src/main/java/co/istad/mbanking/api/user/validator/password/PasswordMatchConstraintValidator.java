package co.istad.mbanking.api.user.validator.password;

import co.istad.mbanking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

@RequiredArgsConstructor
public class PasswordMatchConstraintValidator implements ConstraintValidator<PasswordMatch, Object> {
    // create three field
    private String password;
    private String confirmPassword;
    private String message;

    private final UserMapper userMapper;
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

        // check confirmPassword
        // boolean isValid = passwordValue.equals(password);
        boolean isValid = false;
        if(passwordValue !=null){
            isValid = passwordValue.equals(confirmPasswordValue);
        }

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( message)
                    .addPropertyNode(password)
                    .addConstraintViolation();
            // build message for confirmPassword property
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(confirmPassword)
                    .addConstraintViolation();
        }

        return isValid;
    }

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        // it means PasswordMatch is type annotation is has password and confirmPassword
        this.password=constraintAnnotation.password();
        this.confirmPassword=constraintAnnotation.confirmPassword();
        this.message=constraintAnnotation.message();
    }
}
