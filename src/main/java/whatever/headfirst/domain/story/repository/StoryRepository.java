package whatever.headfirst.domain.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.enums.StoryStatus;
import whatever.headfirst.domain.story.entity.Story;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story,Long> {

    // id + status
    // select * from story where id = ? and status = ? order by desc limit 1
    Optional<Story> findFirstByIdAndStatusOrderByIdDesc(Long id, StoryStatus status);

    // 유효한 편지 리스트
    List<Story> findAllByIdAndStatusOrderByCreatedAtDesc(Long id, StoryStatus status);

    List<Story> findAllByKeywordLikeAndStatusOrderByCreatedAtDesc(String keyword, StoryStatus status);

    @Query(value = "SELECT s FROM Story s WHERE " +
            "FUNCTION('ST_Distance_Sphere', FUNCTION('POINT', s.longitude, s.latitude), " +
            "FUNCTION('POINT', :longitude, :latitude)) <= :radius")
    List<Story> findStoriesWithinRadius(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("radius") Double radius
    );
}