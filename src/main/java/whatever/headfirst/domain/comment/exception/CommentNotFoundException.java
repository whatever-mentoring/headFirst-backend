package whatever.headfirst.domain.comment.exception;

import whatever.headfirst.global.error.ErrorCode;
import whatever.headfirst.global.payload.GeneralException;

public class CommentNotFoundException extends GeneralException {

    private static final ErrorCode errorCode = ErrorCode.COMMENT_NOT_FOUND;

    public CommentNotFoundException() {
        super(errorCode);
    }
}
