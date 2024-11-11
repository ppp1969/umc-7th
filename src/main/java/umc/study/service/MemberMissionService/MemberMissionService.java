package umc.study.service.MemberMissionService;

import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberMissionRequestDTO;

public interface MemberMissionService {
    public MemberMission onBording(MemberMissionRequestDTO.OnBordingDto request);
}
