package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MissionRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    @Override
    public boolean existsById(Long id) {
        return storeRepository.existsById(id);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Optional<Store> store = storeRepository.findById(storeId);
        // pageRequest객체를 생성. 몇번 페이지에서 10개
        // page의 range를 프론트에서 1~N+1으로 주는걸, 0~N으로 변경
        return reviewRepository.findAllByStore(store.get(), PageRequest.of(page-1, 10));
    }

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page) {
        Optional<Store> store = storeRepository.findById(storeId);

        return missionRepository.findAllByStore(store.get(), PageRequest.of(page-1, 10));
    }
}
