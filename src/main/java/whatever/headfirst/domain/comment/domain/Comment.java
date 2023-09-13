package whatever.headfirst.domain.comment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatever.headfirst.domain.comment.dto.CommentRequest;
import whatever.headfirst.domain.comment.dto.CommentResponse;
import whatever.headfirst.domain.common.BaseEntity;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.Story;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    private Comment(Member member, Story story, String content) {
        this.member = member;
        this.story = story;
        this.content = content;
    }

    public static Comment from(Member member, Story story, CommentRequest request) {
        return new Comment(member, story, request.getContent());
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public CommentResponse toDto() {
        return CommentResponse.from(this.id, this.content, this.member, this.story);
    }
}
