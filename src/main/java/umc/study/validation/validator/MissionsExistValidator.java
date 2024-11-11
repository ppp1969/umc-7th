package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.service.MissionService.MissionQueryService;
import umc.study.validation.annotation.ExistCategories;
import umc.study.validation.annotation.ExistMissions;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MissionsExistValidator implements ConstraintValidator<ExistMissions, Long> {

    private final MissionQueryService missionQueryService;

    @Override
    public void initialize(ExistMissions constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) { // 단일 Long 값 처리
        boolean isValid = missionQueryService.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
