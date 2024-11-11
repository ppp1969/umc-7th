package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.study.apiPayload.exception.handler.RegionHandler;
import umc.study.converter.StoreConverter;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.StoreRequestDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor // final 붙은 애들만 생성자 생성!
public class StoreCommandServiceImpl implements StoreCommandService{
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    public Store registerStore(StoreRequestDTO.RegisterDto request, String region){
        Store newStore = StoreConverter.toStore(request, region);

        // region이 과연 Region에 존재하는 필드값인지?
        Optional<Region> byName = regionRepository.findByName(region);
        byName.orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        // 양방향 관계 동기화
        /*
        * 양방향 관계 동기화:
            Store가 Region을 참조하지만, Region의 storeList에는 새로운 Store가 자동으로 추가되지 않는다.
            이를 해결하기 위해 Store에서 Region을 설정할 때, Region의 storeList에도 해당 Store를 추가해야 한다.*/
        byName.get().addStore(newStore);
        return storeRepository.save(newStore);
    }
}
