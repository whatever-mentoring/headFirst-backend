package whatever.headfirst.domain.story.converter;


import whatever.headfirst.domain.comment.dto.CommentResponse;
import whatever.headfirst.domain.common.annotation.Converter;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.controller.model.StoryRegisterRequest;
import whatever.headfirst.domain.story.controller.model.StoryResponse;
import whatever.headfirst.domain.story.entity.Story;
import whatever.headfirst.domain.story.entity.enums.StoryStatus;
import whatever.headfirst.domain.story.exception.StoryNotFoundException;

import java.util.Optional;

@Converter
public class StoryConverter {

    public Story toEntity(
            StoryRegisterRequest request, Member loginMember
    ) {

        return Optional.ofNullable(request)
                .map(it -> {

                    return Story.builder()
                            .title(request.getTitle())
                            .content(request.getContent())
                            .keyword(request.getKeyword())
                            .latitude(request.getLatitude())
                            .longitude(request.getLongitude())
                            .member(loginMember)
                            .build()
                            ;

                })
                .orElseThrow(StoryNotFoundException::new);

    }

    public StoryResponse toResponse(
            Story story, Member loginMember
    ) {
        return Optional.ofNullable(story)
                .map(it -> {
                    return StoryResponse.builder()
                            .id(story.getId())
                            .memberId(loginMember.getId())
                            .title(story.getTitle())
                            .content(story.getContent())
                            .keyword(story.getKeyword())
                            .status(StoryStatus.CREATED)
                            .createdAt(story.getCreatedAt())
                            .latitude(story.getLatitude())
                            .longitude(story.getLongitude())
                            .comments(story.getComments())
                            .heartCount(story.getHeartCount()) // 0으로 초기화
                            .build();
                            })
                            .orElseThrow(StoryNotFoundException::new);
    }


    public StoryResponse toResponse(
            Story story
    ) {
        return Optional.ofNullable(story)
                .map(it -> {
                    return StoryResponse.builder()
                            .id(story.getId())
                            .title(story.getTitle())
                            .content(story.getContent())
                            .keyword(story.getKeyword())
                            .status(StoryStatus.CREATED)
                            .createdAt(story.getCreatedAt())
                            .latitude(story.getLatitude())
                            .longitude(story.getLongitude())
                            .build();
                })
                .orElseThrow(StoryNotFoundException::new);
    }



}
