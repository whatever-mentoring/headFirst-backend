package whatever.headfirst.domain.heart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatever.headfirst.domain.heart.domain.Heart;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    List<Heart> findAllByStoryId(Long storyId);
    List<Heart> findByOwnerIdAndStoryId(Long ownerId, Long storyId);
}
