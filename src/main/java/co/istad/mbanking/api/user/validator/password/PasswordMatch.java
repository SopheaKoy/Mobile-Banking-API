package co.istad.mbanking.api.user.validator.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = PasswordMatchConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PasswordMatch {
    String message() default "The password is not match...!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    // you need to field
    String password();
    String confirmPassword();

    @Target({TYPE})
    @Retention(RUNTIME)
    @interface List {
        PasswordMatch[] value();
    }
}
