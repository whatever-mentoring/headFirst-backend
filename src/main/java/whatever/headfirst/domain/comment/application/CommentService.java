package whatever.headfirst.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatever.headfirst.domain.comment.domain.Comment;
import whatever.headfirst.domain.comment.dto.CommentRequest;
import whatever.headfirst.domain.comment.dto.CommentResponse;
import whatever.headfirst.domain.comment.repository.CommentRepository;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.Story;
import whatever.headfirst.domain.story.exception.StoryNotFoundException;
import whatever.headfirst.domain.story.repository.StoryRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final StoryRepository storyRepository;

    // 댓글 작성
    public CommentResponse write(Member writer, Long storyId, CommentRequest request) {
        Story targetStory = storyRepository.findById(storyId)
                .orElseThrow(StoryNotFoundException::new);

        Comment newComment = Comment.from(writer, targetStory, request);
        Comment saveComment = commentRepository.save(newComment);

        return saveComment.toDto();
    }
}
