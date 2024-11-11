package umc.study.web.dto;

import lombok.Getter;
import umc.study.validation.annotation.ExistMissions;

public class MemberMissionRequestDTO {
    @Getter
    public static class OnBordingDto{
        @ExistMissions
        Long missionId;
    }
}
