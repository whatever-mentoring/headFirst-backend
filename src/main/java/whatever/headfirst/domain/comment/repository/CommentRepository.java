package whatever.headfirst.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatever.headfirst.domain.comment.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByStoryId(Long storyId);
}
