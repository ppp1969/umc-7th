package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Member;
import umc.study.domain.Store;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.RegisterResultDto> register(@RequestBody @Valid StoreRequestDTO.RegisterDto request, @RequestParam(name="region") String region){
        Store store = storeCommandService.registerStore(request, region);
        return ApiResponse.onSuccess(StoreConverter.toRegisterResultDto(store));
    }
}
