package whatever.headfirst.domain.story.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatever.headfirst.domain.story.entity.Story;
import whatever.headfirst.domain.story.entity.enums.StoryStatus;
import whatever.headfirst.domain.story.exception.InternalServerErrorException;
import whatever.headfirst.domain.story.exception.StoryNotFoundException;
import whatever.headfirst.domain.story.repository.StoryRepository;

import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
@Service
public class StoryService {

    private final StoryRepository storyRepository;

    // 특정 아이디를 가진 사연 가져오기.
    public Story getStoryWithThrow(Long id) {

        var entity = storyRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoryStatus.CREATED);
        return entity.orElseThrow(InternalServerErrorException::new);

    }

    // 특정 유저가 작성한 모든 사연 가져오기.
    public List<Story> getStoryByStoryId(Long storyId) {
        var list = storyRepository.findAllByIdAndStatusOrderByCreatedAtDesc(storyId, StoryStatus.CREATED);

        return list;

    }

    public List<Story> getStoryByKeyword(String keyword) {

        String partialKeyword = "%" + keyword + "%";
        var list = storyRepository.findAllByKeywordLikeAndStatusOrderByCreatedAtDesc(partialKeyword, StoryStatus.CREATED);

        return list;
    }

    // 사연 등록.
    public Story register(
            Story story
    ) {
        return Optional.ofNullable(story)
                .map(it -> {
                    it.setStatus(StoryStatus.CREATED);

                    return storyRepository.save(it);
                })
                .orElseThrow(StoryNotFoundException::new);
    }

    public List<Story> findStoriesWithinRadius(double latitude, double longitude, double radius) {
        var list = storyRepository.findStoriesWithinRadius(latitude, longitude, radius);

        return list;
    }

}
