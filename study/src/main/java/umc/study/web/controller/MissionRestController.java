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
import umc.study.converter.StoreConverter;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.service.UserService.UserQueryService;
import umc.study.validation.annotation.ExistMissionStatus;
import umc.study.validation.annotation.ExistStore;
import umc.study.validation.annotation.ExistUser;
import umc.study.web.dto.mission.MissionResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final StoreQueryService storeQueryService;

    private final UserQueryService userQueryService;

    // 특정 가게의 미션 목록 조회
    @PostMapping("/{storeId}")
    @Operation(summary = "가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호"),
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page){
        Page<Mission> missionList = storeQueryService.getMissionList(storeId, page - 1);
        return ApiResponse.onSuccess(StoreConverter.missionPreViewListDTO(missionList));
    }

    // 내가 진행중인 미션 목록 조회
    @GetMapping("/{userId}/")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "특정 회원의 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query string으로 status, page를 주세요")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "userId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "status", description = "미션 상태, query string 입니다!"),
            @Parameter(name = "page", description = "페이지 번호")
    })
    public ApiResponse<MissionResponseDTO.ChallengingMissionListDTO> getChallengingMissionList(@ExistUser @PathVariable(name = "userId") Long userId, @ExistMissionStatus @RequestParam(name = "status") MissionStatus status, @RequestParam(name = "page") Integer page){
        Page<UserMission> userMissionList = userQueryService.getUserMissionList(userId, status, page -1);
        return ApiResponse.onSuccess(UserMissionConverter.challengingMissionListDTO(userMissionList));
    }
}
