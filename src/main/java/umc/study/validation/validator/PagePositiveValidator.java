package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.validation.annotation.PositivePage;

@Component
@RequiredArgsConstructor
public class PagePositiveValidator implements ConstraintValidator<PositivePage, Integer> {
    @Override
    public void initialize(PositivePage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= 1;
    }
}
