package whatever.headfirst.domain.comment.exception;

import whatever.headfirst.global.error.ErrorCode;
import whatever.headfirst.global.payload.GeneralException;

public class CommentAuthorizationException extends GeneralException {

    private static final ErrorCode errorCode = ErrorCode.COMMENT_UNAUTHORIZED;

    public CommentAuthorizationException() {
        super(errorCode);
    }
}
