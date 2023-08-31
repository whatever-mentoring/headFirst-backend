package whatever.headfirst.global.payload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import whatever.headfirst.global.error.ErrorCode;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiErrorResponse> erorrResponse(GeneralException e) {

        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.getStatus();

        return ResponseEntity
                .status(status)
                .body(ApiErrorResponse.of(
                        status,
                        e.getClass().getSimpleName(),
                        errorCode
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> errorResponse(MethodArgumentNotValidException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiErrorResponse.of(
                        HttpStatus.BAD_REQUEST,
                        e.getClass().getSimpleName(),
                        ErrorCode.UNVALID_DTO
                ));
    }
}
