package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.web.dto.review.ReviewRequestDTO;
import umc.study.web.dto.review.ReviewResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResponseDTO.JoinReviewResultDTO> join (
            @PathVariable Long storeId,
            @RequestParam Long userId,
            @RequestBody @Valid ReviewRequestDTO.JoinReviewDto request
    ) {
        Review review = reviewCommandService.joinReview(request, storeId, userId);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }
}
