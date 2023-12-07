package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.review.ReviewRequestDTO;
import umc.study.web.dto.review.ReviewResponseDTO;

public class ReviewConverter {

    public static ReviewResponseDTO.JoinReviewResultDTO toJoinResultDTO(Review review) {
        return ReviewResponseDTO.JoinReviewResultDTO.builder()
                .reviewId(review.getId())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.JoinReviewDto request) {

        return Review.builder()
                .content(request.getContent())
                .rate(request.getRate())
                .build();
    }
}
