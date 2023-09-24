package whatever.headfirst.domain.heart.business;

import lombok.RequiredArgsConstructor;
import whatever.headfirst.domain.common.annotation.Business;
import whatever.headfirst.domain.heart.controller.model.HeartResponse;
import whatever.headfirst.domain.heart.converter.HeartConverter;
import whatever.headfirst.domain.heart.service.HeartService;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.Story;
import whatever.headfirst.domain.story.exception.StoryNotFoundException;
import whatever.headfirst.domain.story.service.StoryService;
import whatever.headfirst.global.holder.MemberContextHolder;

import java.util.List;

@RequiredArgsConstructor
@Business
public class HeartBusiness {

    private final HeartConverter heartConverter;
    private final HeartService heartService;
    private final MemberContextHolder holder;
    private final StoryService storyService;

    public HeartResponse heart(Long storyId) {

        Member loginMember = holder.getMember();
        List<Story> stories = storyService.getStoryByStoryId(storyId);

        if (!stories.isEmpty()) {

            Story story = stories.get(0); // 리스트의 첫 번째 Story 객체 가져오기

            var entity = heartConverter.toEntity(story, loginMember);
            var newEntity = heartService.heart(entity);
            var response = heartConverter.toResponse(newEntity, loginMember);

            return response;

        } else {

            throw new StoryNotFoundException();
        }
    }

    public HeartResponse unHeart(Long storyId) {
        Member loginMember = holder.getMember();
        List<Story> stories = storyService.getStoryByStoryId(storyId);

        if (!stories.isEmpty()) {

            Story story = stories.get(0); // 리스트의 첫 번째 Story 객체 가져오기

            var entity = heartConverter.toEntity(story, loginMember);

            heartService.unHeart(entity);

            var response = heartConverter.toResponse(entity, loginMember); // 여기서 entity를 사용하여 response 생성

            return response;

        } else {

            throw new StoryNotFoundException();
        }

    }
}
