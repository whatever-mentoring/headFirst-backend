package whatever.headfirst.domain.story.controller.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whatever.headfirst.domain.comment.domain.Comment;
import whatever.headfirst.domain.comment.dto.CommentResponse;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.enums.StoryStatus;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryResponse {

    private Long id;

    private Long memberId;

    private String title;

    private String keyword;

    private String content;

    private String latitude;

    private String longitude;

    private List<Comment> comments;

    private Integer heartCount;

    private LocalDateTime createdAt;

    private StoryStatus status;
}
