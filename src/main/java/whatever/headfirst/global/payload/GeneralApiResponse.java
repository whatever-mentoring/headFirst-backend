package whatever.headfirst.global.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GeneralApiResponse {

    private final boolean success;
    private final HttpStatus status;
}
