package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MemberResponseDTO {
    // static으로 만들어야 객체 생성 안하고 활용
    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        LocalDate createAt;
    }
}
