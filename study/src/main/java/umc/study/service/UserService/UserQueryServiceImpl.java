package umc.study.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.ReviewRepository;
import umc.study.repository.UserMissionRepository;
import umc.study.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    private final UserMissionRepository userMissionRepository;

    @Override
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    // 회원이 작성한 리뷰 조회
    @Override
    public Page<Review> getReviewList(Long userId, Integer page) {
        User user = userRepository.findById(userId).get();

        Page<Review> UserPage = reviewRepository.findAllByUser(user, PageRequest.of(page, 10));
        return UserPage;
    }

    // 회원이 진행중인 미션 조회
    @Override
    public Page<UserMission> getUserMissionList(Long userId, MissionStatus missionStatus, Integer page) {
        User user = userRepository.findById(userId).get();

        Page<UserMission> Page = userMissionRepository.findAllByUserAndStatus(user, missionStatus, PageRequest.of(page, 10));
        return Page;
    }

    // 회원 존재 여부
    @Override
    public boolean existUser(Long userId) {
        return userRepository.existsById(userId);
    }

}
