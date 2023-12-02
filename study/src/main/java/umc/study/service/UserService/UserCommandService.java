package umc.study.service.UserService;

import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.UserRequestDTO;

import javax.transaction.Transactional;

public interface UserCommandService {
    @Transactional
    public abstract User joinUser(UserRequestDTO.JoinDto request);

    @Transactional
    public abstract UserMission startMission(UserRequestDTO.StartMissionDTO request, Long usreId);
}
