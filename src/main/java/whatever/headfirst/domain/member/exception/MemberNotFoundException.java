package whatever.headfirst.domain.member.exception;

import whatever.headfirst.global.error.ErrorCode;
import whatever.headfirst.global.payload.GeneralException;

public class MemberNotFoundException extends GeneralException {

    private static final ErrorCode errorCode = ErrorCode.MEMBER_NOT_FOUND;

    public MemberNotFoundException() {
        super(errorCode);
    }
}
