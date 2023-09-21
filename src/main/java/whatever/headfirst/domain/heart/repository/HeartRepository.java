package whatever.headfirst.domain.heart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatever.headfirst.domain.heart.entity.Heart;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.Story;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findFirstByOwnerIdAndStoryId(Member owner, Story story);
}
