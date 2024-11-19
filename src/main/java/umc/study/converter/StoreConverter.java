package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Region;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {
    public static StoreResponseDTO.RegisterResultDto toRegisterResultDto(Store store) {
        return StoreResponseDTO.RegisterResultDto.builder()
                .storeId(store.getId())
                .region(store.getRegion().getName())
                .createdAt(LocalDate.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.RegisterDto request, String region){
        return Store.builder()
                .region(Region.builder().name(region).build())
                .name(request.getName())
                .address(request.getAddress())
                .score(0f)
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDto toReviewPreViewDto(Review review){
        return StoreResponseDTO.ReviewPreViewDto.builder()
                .ownerNickname(review.getMember().getName())
                .body(review.getBody())
                .createdAt(review.getCreatedAt().toLocalDate())
                .score(review.getScore())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDto toReviewPreViewListDto(Page<Review> reviewList){
        List<StoreResponseDTO.ReviewPreViewDto> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::toReviewPreViewDto).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.MissionPreviewDto toMissionPreviewDto(Mission mission){
        return StoreResponseDTO.MissionPreviewDto.builder()
                .storeName(mission.getStore().getName())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .deadline(mission.getDeadline())
                .build();
    }

    public static StoreResponseDTO.MissionPreviewListDto toMissionPreviewListDto(Page<Mission> missionList){
        List<StoreResponseDTO.MissionPreviewDto> missionPreviewDtoList = missionList
                .stream().map(StoreConverter::toMissionPreviewDto).collect(Collectors.toList());

        return StoreResponseDTO.MissionPreviewListDto.builder()
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreviewDtoList.size())
                .missionList(missionPreviewDtoList)
                .build();
    }
}
