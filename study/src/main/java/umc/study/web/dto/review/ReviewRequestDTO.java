package umc.study.web.dto.review;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class ReviewRequestDTO {

    @Getter
    public static class JoinReviewDto {
        @Size(max = 50)
        String content; // 리뷰 내용
        //TODO: 이미지 추가
        @Max(5)
        Float rate; // 별점
    }
}
