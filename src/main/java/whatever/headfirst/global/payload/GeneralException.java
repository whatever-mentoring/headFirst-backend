package whatever.headfirst.global.payload;

import lombok.Getter;
import whatever.headfirst.global.error.ErrorCode;

@Getter
public class GeneralException extends RuntimeException {

    private final ErrorCode errorCode;

    protected  GeneralException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }
}
