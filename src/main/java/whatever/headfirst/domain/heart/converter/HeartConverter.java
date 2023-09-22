package whatever.headfirst.domain.heart.converter;

import lombok.RequiredArgsConstructor;
import whatever.headfirst.domain.common.annotation.Converter;
import whatever.headfirst.domain.heart.controller.model.HeartResponse;
import whatever.headfirst.domain.heart.entity.Heart;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.Story;
import whatever.headfirst.domain.story.entity.enums.StoryStatus;
import whatever.headfirst.domain.story.exception.StoryNotFoundException;
import whatever.headfirst.domain.story.repository.StoryRepository;

@Converter
@RequiredArgsConstructor
public class HeartConverter {

    private final StoryRepository storyRepository;

    public Heart toEntity(
           Story storyId , Member loginMember
    ){


        return Heart.builder()
                .ownerId(loginMember)
                .storyId(storyId)
                .build();
    }

    public HeartResponse toResponse(Heart heart, Member loginMember) {
        return HeartResponse.builder()
                .id(heart.getId())
                .storyId(heart.getStoryId())
                .owner(loginMember.getId())
                .build();
    }
}
