package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.service.ReviewService.ReviewQueryService;
import umc.study.validation.annotation.ExistMembers;
import umc.study.validation.annotation.ExistStores;
import umc.study.validation.annotation.PositivePage;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final ReviewQueryService reviewQueryService;
    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}/reviews") // 보안상 id 노출되지만, jwt 토큰을 활용한 로그인 기능 미구현으로 간단하게 구현
    @Operation(summary = "나의 리뷰 목록 조회 API",description = "나의 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지는 1 이상의 값이어야 함",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "유저 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDto> getMyReviewList(@ExistMembers @PathVariable(name = "memberId") Long memberId
            , @RequestParam(name = "page") @PositivePage Integer page){ // page를 1~n까지 입력받음.
        // 실제론 0~n-1까지 사용
        Page<Review> reviewList = reviewQueryService.getReviewList(memberId, page);
        return ApiResponse.onSuccess(StoreConverter.toReviewPreViewListDto(reviewList));
    }
}
