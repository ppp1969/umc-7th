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
    String message() default "Page는 1이상 이어야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
