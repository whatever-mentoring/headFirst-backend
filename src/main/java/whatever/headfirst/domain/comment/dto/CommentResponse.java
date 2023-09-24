package whatever.headfirst.domain.comment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.Story;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponse {

    private Long commentId;
    private String content;
    private Long memberUuid;
    private Long storyId;
    private String createdAt;

    public CommentResponse(Long commentId, String content, Long memberUuid, Long storyId, String createdAt) {
        this.commentId = commentId;
        this.content = content;
        this.memberUuid = memberUuid;
        this.storyId = storyId;
        this.createdAt = createdAt;
    }

    public static CommentResponse from(Long id, String content, Member member, Story story) {
        return new CommentResponse(id, content, member.getUuid(), story.getId(), story.getCreatedAt());
    }
}
