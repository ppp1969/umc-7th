package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer reward;

    private LocalDate deadline;

    private String missionSpec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    public void addMemberMission(MemberMission memberMission) {
        if (!this.memberMissionList.contains(memberMission)) {
            this.memberMissionList.add(memberMission);
            memberMission.setMission(this); // 양방향 관계 설정
        }
    }

    public void removeMemberMission(MemberMission memberMission) {
        if (this.memberMissionList.contains(memberMission)) {
            this.memberMissionList.remove(memberMission);
            memberMission.setMission(null); // 관계 해제
        }
    }
}
