package modules.be.client.exception.custom;


import modules.be.client.exception.BaseException;
import modules.be.client.exception.ErrorCode;

public class NotFoundHotKeywordExeption extends BaseException {
    public NotFoundHotKeywordExeption() {
        this.code = ErrorCode.NOT_FOUND_HOT_KEYWORD.getCode();
        this.message = ErrorCode.NOT_FOUND_HOT_KEYWORD.getMessage();
        this.status = ErrorCode.NOT_FOUND_HOT_KEYWORD.getStatus();
    }
}
