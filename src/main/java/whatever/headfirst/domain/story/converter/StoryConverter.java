package whatever.headfirst.domain.story.converter;


import lombok.Builder;
import whatever.headfirst.domain.common.annotation.Converter;
import whatever.headfirst.domain.story.controller.model.StoryRegisterRequest;
import whatever.headfirst.domain.story.controller.model.StoryResponse;
import whatever.headfirst.domain.story.entity.Story;
import whatever.headfirst.domain.story.exception.StoryNotFoundException;

import java.util.Optional;

@Converter

public class StoryConverter {

    public Story toEntity(StoryRegisterRequest request) {

        return Optional.ofNullable(request)
                .map(it -> {

                    return Story.builder()
                            .title(request.getTitle())
                            .content(request.getContent())
                            .latitude(request.getLatitude())
                            .longitude(request.getLongitude())
                            .build()
                            ;

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
                            .status(story.getStatus())
                            .createdAt(story.getCreatedAt())
                            .latitude(story.getLatitude())
                            .longitude(story.getLongitude())
                            .build();
                })
                .orElseThrow(StoryNotFoundException::new);
    }


}
