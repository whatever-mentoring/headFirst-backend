package whatever.headfirst.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatever.headfirst.domain.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUuid(Long uuid);
    Optional<Member> findByNickname(String nickname);
}
