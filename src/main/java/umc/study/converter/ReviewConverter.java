package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {
    public static ReviewResponseDTO.CreateReviceResponseDto toCreateResultDto(Review review){
        return ReviewResponseDTO.CreateReviceResponseDto.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .memberId(review.getMember().getId())
                .title(review.getTitle())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.CreateReviewDto request){
        return Review.builder()
                .body(request.getBody())
                .title(request.getTitle())
                .score(request.getScore())
                .build();
    }
}
