package umc.study.web.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MissionResponseDTO {

    // 10주차 특정 가게의 미션 목록 조회
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {
        List<MissionPreViewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {
        Long storeId;
        String storeName;
        Integer reword;
        String description;
        LocalDate deadline;
        LocalDate createdAt;
    }

    // 10주차 내가 진행중인 미션 목록
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengingMissionListDTO {
        List<ChallengingMissionDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengingMissionDTO {
        Long storeId;
        String storeName;
        Long missionId;
        Integer reward;
        String description;
        LocalDate deadline;
        LocalDate createdAt; // 미션 수행 시간
    }
}
