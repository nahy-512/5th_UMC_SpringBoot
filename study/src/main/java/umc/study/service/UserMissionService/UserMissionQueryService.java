package umc.study.service.UserMissionService;

import org.springframework.data.domain.Page;

public interface UserMissionQueryService {
    boolean existUserAndMissionOnStatus(Long memberId, Long missionId);

    boolean existUserMissionId(Long memberMissionId);
}
