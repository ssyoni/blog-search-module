package modules.be.client.exception.custom;

import modules.be.client.exception.BaseException;
import modules.be.client.exception.ErrorCode;

public class NotFoundKeywordException extends BaseException {
    public NotFoundKeywordException() {
        this.code = ErrorCode.NOT_FOUND_KEYWORD.getCode();
        this.message = ErrorCode.NOT_FOUND_KEYWORD.getMessage();
        this.status = ErrorCode.NOT_FOUND_KEYWORD.getStatus();
    }
}
