package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.mission.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMissionConverter {

    // 10주차 내가 진행중인 미션 목록 조회
    public static MissionResponseDTO.ChallengingMissionDTO challengingMissionDTO(UserMission userMission){

        return MissionResponseDTO.ChallengingMissionDTO.builder()
                .missionId(userMission.getMission().getId())
                .storeId(userMission.getMission().getStore().getId())
                .storeName(userMission.getMission().getStore().getName())
                .reward(userMission.getMission().getReward())
                .description(userMission.getMission().getDescription())
                .deadline(userMission.getMission().getDeadline())
                .createdAt(userMission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MissionResponseDTO.ChallengingMissionListDTO challengingMissionListDTO(Page<UserMission> userMissionList){

        List<MissionResponseDTO.ChallengingMissionDTO> result = userMissionList.stream()
                .map(UserMissionConverter::challengingMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.ChallengingMissionListDTO.builder()
                .isLast(userMissionList.isLast())
                .isFirst(userMissionList.isFirst())
                .totalPage(userMissionList.getTotalPages())
                .totalElements(userMissionList.getTotalElements())
                .listSize(result.size())
                .missionList(result)
                .build();
    }
}
