package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}

