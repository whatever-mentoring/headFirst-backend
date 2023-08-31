package whatever.headfirst.global.payload;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import whatever.headfirst.global.error.ErrorCode;
import whatever.headfirst.global.error.ErrorDetail;

@Getter
public class ApiErrorResponse extends GeneralApiResponse {

    private final ErrorDetail error;

    private ApiErrorResponse(HttpStatus status, String type, ErrorCode error) {
        super(false, status);
        this.error = ErrorDetail.of(type, error);
    }

    public static ApiErrorResponse of(HttpStatus status, String type, ErrorCode error) {
        return new ApiErrorResponse(status, type, error);
    }
}
