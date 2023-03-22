package modules.be.client.exception.custom;


import modules.be.client.exception.BaseException;
import modules.be.client.exception.ErrorCode;

public class InvalidPageSizeRequestException extends BaseException {
    public InvalidPageSizeRequestException() {
        this.code = ErrorCode.INVALID_PAGE_SIZE_REQUEST.getCode();
        this.message = ErrorCode.INVALID_PAGE_SIZE_REQUEST.getMessage();
        this.status = ErrorCode.INVALID_PAGE_SIZE_REQUEST.getStatus();
    }
}
