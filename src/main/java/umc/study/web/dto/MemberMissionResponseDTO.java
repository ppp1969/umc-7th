package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.domain.enums.MissionStatus;

import java.time.LocalDate;

public class MemberMissionResponseDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OnBordingResultDto{
        Long memberMissionId;
        Long memberId;
        Long missionId;
        MissionStatus status;
        LocalDate createdAt;
    }
}
