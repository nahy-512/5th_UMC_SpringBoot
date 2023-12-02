package umc.study.web.dto;

import lombok.Getter;
import umc.study.validation.annotation.ExistCategories;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class JoinDto {
        @NotBlank
        String name;    // 이름
        @NotNull
        Integer gender; // 성별
        @NotNull
        Integer birthYear;  // YYYY
        @NotNull
        Integer birthMonth; // MM
        @NotNull
        Integer birthDay;   // DD
        @Size(min = 5, max = 12)
        String address; // 주소
        @Size(min = 5, max = 12)
        String specAddress; // 상세 주소 (동, 호수)
        @ExistCategories
        List<Long> preferCategory; // 선호 음식 종류
    }
}
