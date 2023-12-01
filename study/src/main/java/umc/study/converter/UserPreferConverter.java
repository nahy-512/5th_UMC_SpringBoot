package umc.study.converter;

import umc.study.domain.FoodCategory;
import umc.study.domain.mapping.UserPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class UserPreferConverter {
    // 단방향 연관 관계는 converter에서 설정해도 괜찮음. 하지만 양방향 연관 관계 설정은 converter 보다는 service에서 하는 것이 좋음
    public static List<UserPrefer> toUserPreferList(List<FoodCategory> foodCategoryList) {

        return foodCategoryList.stream()
                .map(foodCategory ->
                        UserPrefer.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
