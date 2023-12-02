package umc.study.domain.mapping;

import lombok.*;
import umc.study.domain.Mission;
import umc.study.domain.Terms;
import umc.study.domain.User;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.MissionStatus;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'CHALLENGING'")
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    /* 연관 관계를 설정하고, 양방향 매핑이 된 경우 연관 관계 편의 메서드를 이용해 양방향 매핑을 해줌 */
    public void setUser(User user) {
        if (this.user != null)
            user.getUserMissionList().remove(this);
        this.user = user;
        user.getUserMissionList().add(this);
    }
}
