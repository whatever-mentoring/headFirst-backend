package whatever.headfirst.domain.story.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatever.headfirst.domain.story.entity.Story;
import whatever.headfirst.domain.story.entity.enums.StoryStatus;
import whatever.headfirst.domain.story.exception.StoryNotFoundException;
import whatever.headfirst.domain.story.repository.StoryRepository;

import java.util.Optional;



@RequiredArgsConstructor
@Service
public class StoryService {

    private final StoryRepository storyRepository;

    // 특정 아이디를 가진 사연 가져오기.
    public Story getStoryWithThrow(Long id) {
        var entity = storyRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoryStatus.CREATED);
        return entity.orElseThrow(StoryNotFoundException::new);

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
}
