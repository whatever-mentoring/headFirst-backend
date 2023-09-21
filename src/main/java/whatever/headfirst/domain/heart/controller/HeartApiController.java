package whatever.headfirst.domain.heart.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whatever.headfirst.domain.heart.business.HeartBusiness;
import whatever.headfirst.domain.heart.controller.model.HeartResponse;
import whatever.headfirst.global.payload.ApiSuccessResponse;

@RestController
@RequestMapping("/api/heart")
@RequiredArgsConstructor
public class HeartApiController {
    private final HeartBusiness heartBusiness;

    @PostMapping("/{storyId}")
    public ResponseEntity<ApiSuccessResponse<HeartResponse>> heart(
            @PathVariable("storyId") Long storyId
    ) {

        var response = heartBusiness.heart(storyId);

        return ApiSuccessResponse.result(HttpStatus.OK, response);
    }

    @DeleteMapping("/{storyId}")
    public ResponseEntity<ApiSuccessResponse<HeartResponse>> unHeart(
            @PathVariable("storyId") Long storyId
    ) {
        var response = heartBusiness.unHeart(storyId);

        return ApiSuccessResponse.result(HttpStatus.OK, response);
    }
}
