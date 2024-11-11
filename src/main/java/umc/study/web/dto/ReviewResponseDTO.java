package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDTO {
    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class CreateReviceResponseDto{
        private Long reviewId;
        private Long storeId;
        private Long memberId;
        private String title;
        private Float score;
        private String body;
        private LocalDateTime createdAt;
    }
}
