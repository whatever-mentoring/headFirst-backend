package whatever.headfirst.domain.heart.exception;

import whatever.headfirst.global.error.ErrorCode;
import whatever.headfirst.global.payload.GeneralException;

public class HeartConflictException extends GeneralException {

    private static final ErrorCode errorCode = ErrorCode.ALREADY_HEARTED;

    public HeartConflictException() {
        super(errorCode);
    }

}
