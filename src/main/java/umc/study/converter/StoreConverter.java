package umc.study.converter;

import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.time.LocalDate;

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
}
