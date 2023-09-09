package whatever.headfirst.domain.story.business;


import lombok.RequiredArgsConstructor;
import whatever.headfirst.domain.common.annotation.Business;
import whatever.headfirst.domain.story.controller.model.StoryRegisterRequest;
import whatever.headfirst.domain.story.controller.model.StoryResponse;
import whatever.headfirst.domain.story.converter.StoryConverter;
import whatever.headfirst.domain.story.service.StoryService;

@RequiredArgsConstructor
@Business
public class StoryBusiness {

    private final StoryService storyService;
    private final StoryConverter storyConverter;

    public StoryResponse register(
            StoryRegisterRequest storyRegisterRequest
    ) {

        //req -> entity -> save -> response.
        var entity =  storyConverter.toEntity(storyRegisterRequest);
        var newEntity = storyService.register(entity);
        var response = storyConverter.toResponse(newEntity);

        return response;

    }


}
