package whatever.headfirst.global.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    UNVALID_DTO(BAD_REQUEST, "DTO 값 오류"),
    MEMBER_NOT_FOUND(NOT_FOUND, "멤버가 없습니다.");

    private final HttpStatus status;
    private final String message;
}
