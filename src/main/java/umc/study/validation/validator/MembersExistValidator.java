package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.MemberRepository;
import umc.study.service.MemberService.MemberQueryService;
import umc.study.validation.annotation.ExistCategories;
import umc.study.validation.annotation.ExistMembers;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MembersExistValidator implements ConstraintValidator<ExistMembers, Long> {

    private final MemberQueryService memberQueryService;
    @Override
    public void initialize(ExistMembers constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = memberQueryService.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
