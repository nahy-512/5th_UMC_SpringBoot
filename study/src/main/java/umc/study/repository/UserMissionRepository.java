package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.study.domain.User;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("SELECT COUNT(um) > 0 FROM UserMission um " +
            "WHERE um.user.id = :userId " +
            "AND um.mission.id = :missionId " +
            "AND um.status = 'CHALLENGING'")
    boolean existsByUserIdAndMissionIdAndStatus(@Param("userId") Long userId, @Param("missionId") Long missionId);

    Page<UserMission> findAllByUserAndStatus(User user, MissionStatus missionStatus, PageRequest pageRequest);

}

