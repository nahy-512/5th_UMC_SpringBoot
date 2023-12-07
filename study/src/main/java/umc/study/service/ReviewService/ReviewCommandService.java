package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.web.dto.review.ReviewRequestDTO;

import javax.transaction.Transactional;

public interface ReviewCommandService {
    @Transactional
    public abstract Review joinReview(ReviewRequestDTO.JoinReviewDto request, Long storeId, Long userId);
}
