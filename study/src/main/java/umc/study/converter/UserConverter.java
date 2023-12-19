package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.domain.enums.Gender;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.review.ReviewResponseDTO;
import umc.study.web.dto.user.UserRequestDTO;
import umc.study.web.dto.user.UserResponseDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 미션 시작
    public static UserResponseDTO.StartMissionResultDTO toMissionResultDTO(UserMission userMission){
        return UserResponseDTO.StartMissionResultDTO.builder()
                .memberId(userMission.getUser().getId())
                .missionId(userMission.getMission().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDto request) {

        Gender gender = null;

        switch (request.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return User.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .userPreferList(new ArrayList<>())
                .build();
    }


    // 10주차 내가 작성한 리뷰 목록 조회
    public static ReviewResponseDTO.MyReviewDTO myReviewDTO(Review review){
        return ReviewResponseDTO.MyReviewDTO.builder()
                .storeName(review.getStore().getName())
                .storeId(review.getStore().getId())
                .ownerNickname(review.getUser().getName())
                .score(review.getRate())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getContent())
                .build();
    }

    public static ReviewResponseDTO.MyReviewListDTO myReviewListDTO(Page<Review> reviewList){

        List<ReviewResponseDTO.MyReviewDTO> myReviewDTOList = reviewList.stream()
                .map(UserConverter::myReviewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.MyReviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(myReviewDTOList.size())
                .reviewList(myReviewDTOList)
                .build();
    }
}
