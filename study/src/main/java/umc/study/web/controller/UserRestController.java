package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.UserConverter;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
import umc.study.service.UserService.UserCommandService;
import umc.study.web.dto.UserRequestDTO;
import umc.study.web.dto.UserResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join (@RequestBody @Valid UserRequestDTO.JoinDto request) {
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }


    @PostMapping("/{userId}/missions")
    public ApiResponse<UserResponseDTO.StartMissionResultDTO> missionPerform (
            @PathVariable Long userId,
            @RequestBody @Valid UserRequestDTO.StartMissionDTO request
    ){
        UserMission userMission = userCommandService.startMission(request, userId);
        return ApiResponse.onSuccess(UserConverter.toMissionResultDTO(userMission));
    }
}
