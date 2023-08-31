package whatever.headfirst.global.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    UNVALID_DTO(BAD_REQUEST, "DTO 값 오류");

    private final HttpStatus status;
    private final String message;
}
