package whatever.headfirst.domain.story.controller.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.enums.StoryStatus;

import java.awt.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryResponse {

    private Long id;

    private Long memberId;

    private String title;

    private String content;

    private double latitude;

    private double longitude;

    private LocalDateTime createdAt;

    private StoryStatus status;
}
