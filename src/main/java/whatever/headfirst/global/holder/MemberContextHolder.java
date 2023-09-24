package whatever.headfirst.global.holder;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.member.exception.MemberNotFoundException;
import whatever.headfirst.domain.member.repository.MemberRepository;

@Component
@RequiredArgsConstructor
public class MemberContextHolder {

    private final MemberRepository memberRepository;

    public Member getMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nickname = authentication.getName();
        return memberRepository.findByNickname(nickname)
                .orElseThrow(MemberNotFoundException::new);
    }
}
