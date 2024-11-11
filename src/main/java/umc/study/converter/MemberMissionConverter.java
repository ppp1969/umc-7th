package umc.study.converter;

import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberMissionRequestDTO;
import umc.study.web.dto.MemberMissionResponseDTO;

import java.time.LocalDate;

public class MemberMissionConverter {
    public static MemberMissionResponseDTO.OnBordingResultDto toOnBordingDto(MemberMission memberMission){
        return MemberMissionResponseDTO.OnBordingResultDto.builder()
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus())
                .createdAt(LocalDate.now())
                .build();
    }

    public static MemberMission toMemberMission(MemberMissionRequestDTO.OnBordingDto request){
        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
