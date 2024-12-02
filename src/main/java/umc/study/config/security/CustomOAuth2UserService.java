package umc.study.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.Role;
import umc.study.repository.MemberRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 공급자 정보 확인
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String nickname;
        String emailDomain;

        // 공급자별 사용자 정보 처리
        switch (registrationId) {
            case "kakao":
                Map<String, Object> kakaoProperties = (Map<String, Object>) attributes.get("properties");
                nickname = (String) kakaoProperties.get("nickname");
                emailDomain = "@kakao.com";
                break;
            case "google":
                nickname = (String) attributes.get("name"); // Google의 닉네임은 name 필드
                emailDomain = "@google.com";
                break;
            case "naver":
                Map<String, Object> naverResponse = (Map<String, Object>) attributes.get("response");
                nickname = (String) naverResponse.get("nickname");
                emailDomain = "@naver.com";
                break;
            default:
                throw new IllegalArgumentException("Unsupported provider: " + registrationId);
        }

        // 이메일 생성
        String email = nickname + emailDomain;

        // 사용자 정보 저장 또는 업데이트
        Member member = saveOrUpdateUser(email, nickname);

        // 이메일을 Principal로 사용하기 위해 attributes 수정
        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
        modifiedAttributes.put("email", email);

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                modifiedAttributes,
                "email" // email을 Principal로 설정
        );
    }

    private Member saveOrUpdateUser(String email, String nickname) {
        Member member = memberRepository.findByEmail(email)
                .orElse(Member.builder()
                        .email(email)
                        .name(nickname)
                        .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID()))
                        .gender(Gender.NONE)  // 기본값 설정
                        .address("소셜로그인")  // 기본값 설정
                        .specAddress("소셜로그인")  // 기본값 설정
                        .role(Role.USER)
                        .build());

        return memberRepository.save(member);
    }
}