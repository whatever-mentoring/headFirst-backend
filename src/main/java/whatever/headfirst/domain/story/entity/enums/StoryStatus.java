package whatever.headfirst.domain.story.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoryStatus {

    CREATED("등록"),
    DELETED("삭제"),
    ;


    private String description;
}
