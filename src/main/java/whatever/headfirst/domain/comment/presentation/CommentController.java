package whatever.headfirst.domain.comment.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whatever.headfirst.domain.comment.application.CommentService;
import whatever.headfirst.domain.comment.dto.CommentRequest;
import whatever.headfirst.domain.comment.dto.CommentResponse;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.global.holder.MemberContextHolder;
import whatever.headfirst.global.payload.ApiSuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<CommentResponse>> write(@PathVariable Long id,
                                                                     @RequestBody @Valid CommentRequest request) {
        Member loginMember = MemberContextHolder.getMember();
        return ApiSuccessResponse.result(HttpStatus.CREATED, commentService.write(loginMember, id, request));
    }
}
