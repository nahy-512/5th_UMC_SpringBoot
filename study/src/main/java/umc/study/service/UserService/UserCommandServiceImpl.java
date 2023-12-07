package umc.study.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.study.converter.UserConverter;
import umc.study.converter.UserPreferConverter;
import umc.study.domain.FoodCategory;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
import umc.study.domain.mapping.UserPrefer;
import umc.study.repository.FoodCategoryRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.UserMissionRepository;
import umc.study.repository.UserRepository;
import umc.study.web.dto.user.UserRequestDTO;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional
    public User joinUser (UserRequestDTO.JoinDto request) {

        User newUser = UserConverter.toUser(request);
        // FoodCategory의 리스트 얻기
        //TODO: stream 사용법 숙지하기
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<UserPrefer> userPreferList = UserPreferConverter.toUserPreferList(foodCategoryList);

        userPreferList.forEach(userPrefer -> {userPrefer.setUser(newUser);});

        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public UserMission startMission(UserRequestDTO.StartMissionDTO request, Long userId) {

        // 유저 조회
        User findUser = userRepository.findById(userId).orElseThrow(
                //TODO: Validation 처리
        );

        // 미션 조회
        Mission findMission = missionRepository.findById(request.getMissionId()).orElseThrow(
                //TODO: Validation 처리
        );

        UserMission userMission = UserMission.builder()
                .user(findUser)
                .mission(findMission)
                .build();

        return userMissionRepository.save(userMission);
    }
}
