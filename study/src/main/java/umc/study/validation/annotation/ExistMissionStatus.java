package umc.study.validation.annotation;

import umc.study.validation.validator.MissionStatusExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionStatusExistValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMissionStatus {

    String message() default "MissionStatus에 존재하는 미션 상태가 아닙니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
