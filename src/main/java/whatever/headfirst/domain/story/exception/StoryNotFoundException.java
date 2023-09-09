package whatever.headfirst.domain.story.exception;

import whatever.headfirst.global.error.ErrorCode;
import whatever.headfirst.global.payload.GeneralException;

public class StoryNotFoundException extends GeneralException {

    private static final ErrorCode errorCode = ErrorCode.STORY_NOT_FOUND;

    public StoryNotFoundException() {
        super(errorCode);
    }
}
