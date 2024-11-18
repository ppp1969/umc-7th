package umc.study.service.ReviewService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import umc.study.domain.Review;
import umc.study.domain.Store;

import java.util.Optional;

public interface ReviewQueryService {
    public Page<Review> getReviewList(Long memberId, Integer page);
}
