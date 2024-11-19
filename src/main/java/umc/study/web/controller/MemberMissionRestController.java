package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberMissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberMissionService.MemberMissionService;
import umc.study.validation.annotation.ExistStores;
import umc.study.validation.annotation.PositivePage;
import umc.study.web.dto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions/my")
public class MemberMissionRestController {

    private final MemberMissionService memberMissionService;

    @Operation(summary = "미션 등록하는 API",description = "하드코딩된 1번 유저에게 특정 미션 도전중으로 등록하는 API")
    @PostMapping("/on-bording")
    public ApiResponse<MemberMissionResponseDTO.OnBordingResultDto> onBording(@RequestBody @Valid MemberMissionRequestDTO.OnBordingDto request){
        MemberMission memberMission = memberMissionService.onBording(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toOnBordingDto(memberMission));
    }



    @GetMapping("/on-bording")
    @Operation(summary = "내가 진행중인 리뷰 목록 조회 API",description = "하드코딩된 1번 유저의 진행중인 미션을 조회하는 API, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지는 1 이상의 값이어야 함",content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ApiResponse<MemberMissionResponseDTO.MemberMissionListDto> getOnBordingMissionList(@RequestParam(name = "page") @PositivePage Integer page){ // page를 1~n까지 입력받음.
        Page<MemberMission> memberMissionList = memberMissionService.getMemberMissionList(1L, page);
        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionListDto(memberMissionList));
    }

}
