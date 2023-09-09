package whatever.headfirst.domain.story.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whatever.headfirst.domain.story.business.StoryBusiness;
import whatever.headfirst.domain.story.controller.model.StoryRegisterRequest;
import whatever.headfirst.domain.story.controller.model.StoryResponse;
import whatever.headfirst.global.payload.ApiSuccessResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/story")
public class StoryApiController {

    private final StoryBusiness storyBusiness;

    @PostMapping("/register")
    public ResponseEntity<ApiSuccessResponse<StoryResponse>> register(
            @Valid
            @RequestBody StoryRegisterRequest request
    ) {

        var response = storyBusiness.register(request); // StoryRegisterRequest 객체를 인수로 전달

        return ApiSuccessResponse.result(HttpStatus.CREATED, response);

    }
}
