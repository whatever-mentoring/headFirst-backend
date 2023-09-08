package whatever.headfirst.domain.auth.exception;

import whatever.headfirst.global.error.ErrorCode;
import whatever.headfirst.global.payload.GeneralException;

public class KakaoTokenException extends GeneralException {

    private static final ErrorCode errorCode = ErrorCode.KAKAO_EXCEPTION;

    public KakaoTokenException() {
        super(errorCode);
    }
}
