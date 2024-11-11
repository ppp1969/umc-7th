package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    public Review createRevice(Long storeId, ReviewRequestDTO.CreateReviewDto request); // save로 리턴
}
