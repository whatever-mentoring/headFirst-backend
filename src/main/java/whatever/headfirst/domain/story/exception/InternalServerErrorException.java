package whatever.headfirst.domain.story.exception;

import whatever.headfirst.global.error.ErrorCode;
import whatever.headfirst.global.payload.GeneralException;

public class InternalServerErrorException extends GeneralException {

    private static final ErrorCode errorCode = ErrorCode.NULL_POINT;

    public InternalServerErrorException() {
        super(errorCode);
    }
}
