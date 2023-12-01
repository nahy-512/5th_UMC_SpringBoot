package umc.study.domain.mapping;

import lombok.*;
import umc.study.domain.FoodCategory;
import umc.study.domain.User;
import umc.study.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* 연관 관계 매핑 (단방향 매핑) */
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    /* 연관 관계를 설정하고, 양방향 매핑이 된 경우 연관 관계 편의 메서드를 이용해 양방향 매핑을 해줌 */
    public void setUser(User user) {
        if (this.user != null)
            user.getUserPreferList().remove(this);
        this.user = user;
        user.getUserPreferList().add(this);
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }
}
