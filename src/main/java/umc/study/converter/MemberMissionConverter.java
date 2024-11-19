package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberMissionRequestDTO;
import umc.study.web.dto.MemberMissionResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MemberMissionResponseDTO.MemberMissionDto toMemberMissionDto(MemberMission memberMission){
        return MemberMissionResponseDTO.MemberMissionDto.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .createdAt(memberMission.getCreatedAt().toLocalDate())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getStatus())
                .build();
    }

    public static MemberMissionResponseDTO.MemberMissionListDto toMemberMissionListDto(Page<MemberMission> missionList){
        List<MemberMissionResponseDTO.MemberMissionDto> list = missionList.stream().map(MemberMissionConverter::toMemberMissionDto).collect(Collectors.toList());

        return MemberMissionResponseDTO.MemberMissionListDto.builder()
                .missionList(list)
                .listSize(list.size())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }
}
