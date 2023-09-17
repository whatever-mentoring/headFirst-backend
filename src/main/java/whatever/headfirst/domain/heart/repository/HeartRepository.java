package whatever.headfirst.domain.heart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatever.headfirst.domain.heart.domain.Heart;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    List<Heart> findAllByStoryId(Long storyId);
    Optional<Heart> findByOwnerIdAndStoryId(Long ownerId, Long storyId);
}
