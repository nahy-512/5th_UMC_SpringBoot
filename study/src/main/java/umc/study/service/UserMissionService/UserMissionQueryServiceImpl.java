package umc.study.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.repository.UserMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryServiceImpl implements UserMissionQueryService{

    private final UserMissionRepository userMissionRepository;

    @Override
    public boolean existUserAndMissionOnStatus(Long userId, Long missionId) {
        return userMissionRepository.existsByUserIdAndMissionIdAndStatus(userId, missionId);
    }

    @Override
    public boolean existUserMissionId(Long userMissionId) {
        return userMissionRepository.existsById(userMissionId);
    }
}
