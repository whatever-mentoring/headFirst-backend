package whatever.headfirst.domain.story.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import whatever.headfirst.domain.story.business.StoryBusiness;
import whatever.headfirst.domain.story.controller.model.StoryRegisterRequest;
import whatever.headfirst.domain.story.controller.model.StoryResponse;
import whatever.headfirst.global.payload.ApiSuccessResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/story")
public class StoryApiController {

    private final StoryBusiness storyBusiness;

    // 사연 등록.
    @PostMapping("/register")
    public ResponseEntity<ApiSuccessResponse<StoryResponse>> register(
            @Valid
            @RequestBody StoryRegisterRequest request
    ) {

        var response = storyBusiness.register(request);

        return ApiSuccessResponse.result(HttpStatus.OK, response);

    }

    // 특정 유저가 작성한 사연 전체 조회.
    @GetMapping("/search/{storyId}")
    public ResponseEntity<ApiSuccessResponse<List<StoryResponse>>> search(
            @PathVariable Long storyId) {
        var response = storyBusiness.search(storyId);

        return ApiSuccessResponse.result(HttpStatus.OK, response);
    }

    // 키워드 별 사연 검색
    @GetMapping("/search")
    public ResponseEntity<ApiSuccessResponse<List<StoryResponse>>> searchByKeyword(
            @RequestParam("keyword") String keyword
    ) {
        var response = storyBusiness.searchByKeyword(keyword);

        return ApiSuccessResponse.result(HttpStatus.OK, response);
    }

    @GetMapping("/in-radius")
    public ResponseEntity<ApiSuccessResponse<List<StoryResponse>>> findStoriesWithinRadius(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("radius") double radius
    ) {
        var response = storyBusiness.findStoriesWithinRadius(latitude, longitude, radius);

        return ApiSuccessResponse.result(HttpStatus.OK, response);
    }
}