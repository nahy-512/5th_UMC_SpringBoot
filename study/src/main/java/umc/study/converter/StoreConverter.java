package umc.study.converter;

import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.review.ReviewResponseDTO;
import umc.study.web.dto.store.StoreRequestDTO;
import umc.study.web.dto.store.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class StoreConverter {

    public static StoreResponseDTO.JoinStoreResultDTO toJoinResultDTO(Store store) {
        return StoreResponseDTO.JoinStoreResultDTO.builder()
                .storeId(store.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.JoinStoreDto request) {

        return Store.builder()
                .name(request.getName())
                .information(request.getInformation())
                .address(request.getAddress())
                .category(request.getCategory())
                .build();
    }

    // 10주차 가게 리뷰 목록 조회
    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return null;
    }
    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(List<Review> reviewList){
        return null;
    }
}
