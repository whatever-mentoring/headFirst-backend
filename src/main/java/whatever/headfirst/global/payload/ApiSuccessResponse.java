package whatever.headfirst.global.payload;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiSuccessResponse<T> extends GeneralApiResponse {

    private final T data;

    private ApiSuccessResponse(HttpStatus status, T data) {
        super(true, status);
        this.data = data;
    }

    private static <T>ApiSuccessResponse<T> of(HttpStatus status, T data) {
        return new ApiSuccessResponse<>(status, data);
    }

    public static <T>ResponseEntity<ApiSuccessResponse<T>> result(HttpStatus status, T data) {
        ApiSuccessResponse<T> response = ApiSuccessResponse.of(status, data);
        return ResponseEntity.status(status)
                .body(response);
    }
}
