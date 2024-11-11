package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.validation.annotation.ExistStores;

public class ReviewRequestDTO {
    @Getter
    public static class CreateReviewDto{

        @NotNull
        @Size(min = 5, max = 20)
        private String title;

        @NotNull
        private Float score;

        @NotNull
        @Size(min = 5, max = 100)
        private String body;
    }
}
