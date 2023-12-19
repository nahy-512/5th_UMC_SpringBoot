package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.converter.StoreConverter;
import umc.study.converter.UserConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.service.UserService.UserCommandService;
import umc.study.service.UserService.UserQueryService;
import umc.study.validation.annotation.ExistStore;
import umc.study.validation.annotation.ExistUser;
import umc.study.web.dto.review.ReviewRequestDTO;
import umc.study.web.dto.review.ReviewResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    private final UserQueryService userQueryService;

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResponseDTO.JoinReviewResultDTO> join (
            @PathVariable Long storeId,
            @RequestParam Long userId,
            @RequestBody @Valid ReviewRequestDTO.JoinReviewDto request
    ) {
        Review review = reviewCommandService.joinReview(request, storeId, userId);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }

    // 내가 작성한 리뷰 목록 조회
    // 리뷰 목록 조회
    @GetMapping("/{userId}")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디, path variable 입니다!"), // 임의로 토큰 대신 userId
            @Parameter(name = "page", description = "페이지 번호"),
    })
    public ApiResponse<ReviewResponseDTO.MyReviewListDTO> getReviewList(@ExistUser @PathVariable(name = "userId") Long userId, @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = userQueryService.getReviewList(userId, page - 1);
        return ApiResponse.onSuccess(UserConverter.myReviewListDTO(reviewList));
    }
}
