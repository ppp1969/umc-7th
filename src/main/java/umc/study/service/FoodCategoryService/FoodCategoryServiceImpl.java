package umc.study.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.repository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService{
    private final FoodCategoryRepository foodCategoryRepository;
    @Override
    public boolean existsById(Long id) {
        // 추가적인 비즈니스 로직이 들어갈 수 있음
        return foodCategoryRepository.existsById(id);
    }
}


