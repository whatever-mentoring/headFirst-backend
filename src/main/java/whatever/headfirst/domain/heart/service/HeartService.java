package whatever.headfirst.domain.heart.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whatever.headfirst.domain.heart.entity.Heart;
import whatever.headfirst.domain.heart.exception.HeartConflictException;
import whatever.headfirst.domain.heart.exception.HeartException;
import whatever.headfirst.domain.heart.repository.HeartRepository;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.Story;
import whatever.headfirst.domain.story.entity.enums.StoryStatus;
import whatever.headfirst.domain.story.exception.StoryNotFoundException;
import whatever.headfirst.domain.story.repository.StoryRepository;
import whatever.headfirst.domain.story.service.StoryService;
import whatever.headfirst.global.holder.MemberContextHolder;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final StoryService storyService;
    private final MemberContextHolder holder;
    private final StoryRepository storyRepository;

    @Transactional
    public Heart heart(Heart heart) {

        Member loginMember = holder.getMember();
        List<Story> stories = storyService.getStoryByStoryId(heart.getStoryId());

        if (!stories.isEmpty()) {
            Story story = stories.get(0);

            Optional<Heart> existingHeart = heartRepository.findFirstByOwnerIdAndStoryId(loginMember, story);

            if (existingHeart.isPresent()) {
                throw new HeartConflictException();
            }

            updateHeartCount(heart.getStoryId(), 1);


        }

        return Optional.ofNullable(heart)
                .map(it -> {
                    return heartRepository.save(it);
                })
                .orElseThrow(HeartException::new);
    }

    public void unHeart(Heart heart) {

        Member loginMember = holder.getMember();
        List<Story> stories = storyService.getStoryByStoryId(heart.getStoryId());

        if (!stories.isEmpty()) {
            Story story = stories.get(0);

            Optional<Heart> existingHeart = heartRepository.findFirstByOwnerIdAndStoryId(loginMember, story);

            if (existingHeart.isPresent()) {

                updateHeartCount(heart.getStoryId(), -1);

                heartRepository.delete(existingHeart.get());

            } else {
                throw new StoryNotFoundException();

            }
        }
    }


    @Transactional
    public void updateHeartCount(Long storyId, Integer plusOrMinus) {
        List<Story> stories = storyService.getStoryByStoryId(storyId);

        if (!stories.isEmpty()) {
            Story story = stories.get(0);

            int currentHeartCount = story.getHeartCount() != null ? story.getHeartCount() : 0;
            int newHeartCount = currentHeartCount + plusOrMinus;

            story.setHeartCount(newHeartCount);

            var res = storyRepository.save(story);

        } else {
            throw new StoryNotFoundException();
        }

    }
}
