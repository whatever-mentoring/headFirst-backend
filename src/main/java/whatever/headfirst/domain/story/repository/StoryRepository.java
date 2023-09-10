package whatever.headfirst.domain.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.enums.StoryStatus;
import whatever.headfirst.domain.story.entity.Story;

import java.util.List;
import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story,Long> {

    // id + status
    // select * from story where id = ? and status = ? order by desc limit 1

    Optional<Story> findFirstByIdAndStatusOrderByIdDesc(Long id, StoryStatus status);


    // 유효한 편지 리스트
    // select * from store where status = ? order by desc
    List<Story> findAllByMemberIdAndStatusOrderByCreatedAtDesc(Long memberId, StoryStatus status);
}
