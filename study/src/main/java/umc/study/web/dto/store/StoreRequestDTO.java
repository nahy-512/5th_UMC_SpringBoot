package umc.study.web.dto.store;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StoreRequestDTO {

    @Getter
    public static class JoinStoreDto {
        @NotBlank
        String name;    // 가게 이름
        @Size(max = 50)
        String information; // 설명
        @Size(min = 5, max = 40)
        String address; // 주소
        @Size(max = 15)
        String category; // ex. 중식당
    }
}
