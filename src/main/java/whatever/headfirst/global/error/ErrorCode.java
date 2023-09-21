package whatever.headfirst.global.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    UNVALID_DTO(BAD_REQUEST, "DTO 값 오류"),
    MEMBER_NOT_FOUND(NOT_FOUND, "멤버가 없습니다."),
    KAKAO_EXCEPTION(BAD_REQUEST, "KAKAO 토큰과 관련된 예외가 발생했습니다."),
    STORY_NOT_FOUND(NOT_FOUND, "해당 스토리가 존재하지 않습니다."),
    COMMENT_NOT_FOUND(NOT_FOUND, "해당 댓글이 없습니다."),
    COMMENT_UNAUTHORIZED(UNAUTHORIZED, "해당 댓글의 작성자가 아닙니다."),
    NULL_POINT(INTERNAL_SERVER_ERROR, "내부적 서버 에러"),
    HEART_EXCEPTION(BAD_REQUEST, "좋아요 관련된 예외가 발생했습니다."),

    ALREADY_HEARTED(BAD_REQUEST, "이미 등록된 좋아요 내역이 있습니다.");

    private final HttpStatus status;
    private final String message;
}
