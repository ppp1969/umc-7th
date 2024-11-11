package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.service.ReviewService.ReviewCommandServiceImpl;
import umc.study.validation.annotation.ExistStores;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequestMapping("/stores/{storeId}/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ApiResponse<ReviewResponseDTO.CreateReviceResponseDto> createReview(
            @PathVariable @ExistStores Long storeId,
            @RequestBody @Valid ReviewRequestDTO.CreateReviewDto request) {

        Review review = reviewCommandService.createRevice(storeId, request);
        return ApiResponse.onSuccess(ReviewConverter.toCreateResultDto(review));
    }
}