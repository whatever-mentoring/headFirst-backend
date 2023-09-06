package whatever.headfirst.domain.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.member.dto.MemberLoginRequest;
import whatever.headfirst.domain.member.dto.MemberRequest;
import whatever.headfirst.domain.member.exception.MemberNotFoundException;
import whatever.headfirst.domain.member.repository.MemberRepository;
import whatever.headfirst.domain.token.dto.TokenResponse;
import whatever.headfirst.global.jwt.TokenProvider;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public Member signup(MemberRequest request) {

        return memberRepository.findByUuid(request.getUuid())
                .orElseGet(() ->
                    createMember(request.getUuid(), request.getEmail(), request.getNickname())
                );
    }

    public TokenResponse login(MemberLoginRequest request) {

        Member existMember = memberRepository.findByUuid(request.getUuid())
                .orElseThrow(MemberNotFoundException::new);
        String token = tokenProvider.createToken(existMember);
        return TokenResponse.from(token);
    }

    private Member createMember(Long uuid, String email, String nickname) {
        Member createMember = Member
                                .builder()
                                .uuid(uuid)
                                .email(email)
                                .nickname(nickname)
                                .build();
        return memberRepository.save(createMember);
    }
}
