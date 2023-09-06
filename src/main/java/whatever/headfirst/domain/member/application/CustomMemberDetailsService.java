package whatever.headfirst.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import whatever.headfirst.domain.member.exception.MemberNotFoundException;
import whatever.headfirst.domain.member.repository.MemberRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String uuid){
        memberRepository.findByUuid(Long.parseLong(uuid)).orElseThrow(MemberNotFoundException::new);
        return createMember(uuid);
    }

    private User createMember(String uuid) {
        return new User(uuid, "", new ArrayList<>());
    }
}
