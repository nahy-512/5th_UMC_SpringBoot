package umc.study.converter;

import umc.study.domain.User;
import umc.study.domain.enums.Gender;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.user.UserRequestDTO;
import umc.study.web.dto.user.UserResponseDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
}
