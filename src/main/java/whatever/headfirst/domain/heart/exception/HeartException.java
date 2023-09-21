package whatever.headfirst.domain.heart.exception;

import whatever.headfirst.global.error.ErrorCode;
import whatever.headfirst.global.payload.GeneralException;

public class HeartException extends GeneralException {

    private static final ErrorCode errorCode = ErrorCode.HEART_EXCEPTION;


    public HeartException() {
        super(errorCode);
    }

}
