package umc.study.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.service.UserMissionService.UserMissionQueryService;
import umc.study.validation.annotation.CheckChallengingMission;
import umc.study.web.dto.user.UserRequestDTO;
import umc.study.web.dto.user.UserResponseDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ChallengingMissionCheckValidator implements ConstraintValidator<CheckChallengingMission, UserRequestDTO.StartMissionDTO> {

    private final UserMissionQueryService userMissionQueryService;

    @Override
    public void initialize(CheckChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRequestDTO.StartMissionDTO request, ConstraintValidatorContext context) {
        boolean valid = userMissionQueryService.existUserAndMissionOnStatus(request.getUserId(), request.getMissionId());

        if (!valid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_MISSION_NOT_CHALLENGING.toString()).addConstraintViolation();
        }
        return valid;
    }
}

