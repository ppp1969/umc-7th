package umc.study.service.MemberMissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.converter.MemberMissionConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.web.dto.MemberMissionRequestDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberMissionServiceImpl implements MemberMissionService{
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    private final Long hardCodedMemberId = 1L; // 하드코딩된 유저 ID

    @Transactional
    @Override
    public MemberMission onBording(MemberMissionRequestDTO.OnBordingDto request) {
        // Member 찾기
        Optional<Member> member = memberRepository.findById(hardCodedMemberId);
        // Mission 찾기
        Optional<Mission> mission = missionRepository.findById(request.getMissionId());

        // MemberMission 생성
        MemberMission memberMission = MemberMissionConverter.toMemberMission(request);

        memberMission.setMember(member.get());
        memberMission.setMission(mission.get());

        // 양방향 관계 설정 (N에서 설정해줌)
        member.get().addMemberMission(memberMission);
        mission.get().addMemberMission(memberMission);

        return memberMissionRepository.save(memberMission);
    }

    @Override
    public Page<MemberMission> getMemberMissionList(Long memberId, Integer page) {
        Optional<Member> member = memberRepository.findById(memberId);
        return memberMissionRepository.findAllByMember(member.get(), PageRequest.of(page - 1, 10));

    }
}
