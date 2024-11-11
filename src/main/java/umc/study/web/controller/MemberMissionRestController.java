package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberMissionConverter;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberMissionService.MemberMissionService;
import umc.study.web.dto.MemberMissionRequestDTO;
import umc.study.web.dto.MemberMissionResponseDTO;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions/my")
public class MemberMissionRestController {

    private final MemberMissionService memberMissionService;

    @PostMapping("/on-bording")
    public ApiResponse<MemberMissionResponseDTO.OnBordingResultDto> onBording(@RequestBody @Valid MemberMissionRequestDTO.OnBordingDto request){
        MemberMission memberMission = memberMissionService.onBording(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toOnBordingDto(memberMission));
    }
}
