package whatever.headfirst.domain.story.business;


import lombok.RequiredArgsConstructor;
import whatever.headfirst.domain.common.annotation.Business;
import whatever.headfirst.domain.heart.controller.model.HeartResponse;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.controller.model.StoryRegisterRequest;
import whatever.headfirst.domain.story.controller.model.StoryResponse;
import whatever.headfirst.domain.story.converter.StoryConverter;
import whatever.headfirst.domain.story.service.StoryService;
import whatever.headfirst.global.holder.MemberContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class StoryBusiness {

    private final StoryService storyService;
    private final StoryConverter storyConverter;

    public StoryResponse register(
            StoryRegisterRequest storyRegisterRequest
    ) {
        Member loginMember = MemberContextHolder.getMember();
        //req -> entity -> save -> response.
        var entity = storyConverter.toEntity(storyRegisterRequest, loginMember);
        var newEntity = storyService.register(entity);
        var response = storyConverter.toResponse(newEntity, loginMember);

        return response;

    }

    public List<StoryResponse> search(Long storyId) {

        Member loginMember = MemberContextHolder.getMember();

        var list = storyService.getStoryByStoryId(storyId);

        return list.stream()
                .map(story -> storyConverter.toResponse(story,loginMember))
                .collect(Collectors.toList());
    }

    public List<StoryResponse> searchByKeyword(String keyword) {

        Member loginMember = MemberContextHolder.getMember();

        var list = storyService.getStoryByKeyword(keyword);

        return list.stream()
                .map(story -> storyConverter.toResponse(story,loginMember))
                .collect(Collectors.toList());
    }

    public List<StoryResponse> findStoriesWithinRadius(double latitude, double longitude, double radius) {

        var list = storyService.findStoriesWithinRadius(latitude,longitude, radius);

        return list.stream()
                .map(storyConverter::toResponse)
                .collect(Collectors.toList());

    }
}
