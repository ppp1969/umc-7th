package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.enums.Gender;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createAt(LocalDate.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request){
        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
