package umc.study.converter;

import umc.study.domain.FoodCategory;
import umc.study.domain.mapping.MemberPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {
    // 얻은 FoodCategory 객체 리스트로 MemberPrefer List 만들기.
    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategoryList){
        return foodCategoryList.stream().map(foodCategory -> MemberPrefer.builder()
                .foodCategory(foodCategory)
                .build())
                .collect(Collectors.toList());
    }
}
