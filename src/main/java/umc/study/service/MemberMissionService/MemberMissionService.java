package umc.study.service.MemberMissionService;

import org.springframework.data.domain.Page;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberMissionRequestDTO;

public interface MemberMissionService {
    public MemberMission onBording(MemberMissionRequestDTO.OnBordingDto request);
    Page<MemberMission> getMemberMissionList(Long memberId, Integer page);
}
