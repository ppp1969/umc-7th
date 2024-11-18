package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.CategoriesExistValidator;
import umc.study.validation.validator.PagePositiveValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PagePositiveValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE} )
@Retention(RetentionPolicy.RUNTIME)
public @interface PositivePage {
    String message() default "PAGE4001";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
