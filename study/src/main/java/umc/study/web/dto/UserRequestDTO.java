package umc.study.web.dto;

import lombok.Getter;

import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class JoinDto {
        String name;    // 이름
        Integer gender; // 성별
        Integer birthYear;  // YYYY
        Integer birthMonth; // MM
        Integer birthDay;   // DD
        String address; // 주소
        String specAddress; // 상세 주소 (동, 호수)
        List<Long> preferCategory; // 선호 음식 종류
    }
}
