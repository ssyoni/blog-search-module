package modules.be.client.common.exception.custom;


import modules.be.client.common.exception.BaseException;
import modules.be.client.common.exception.ErrorCode;

public class InvalidParameterException extends BaseException {

    public InvalidParameterException() {
        this.code = ErrorCode.QUERY_NOT_FOUND.getCode();
        this.message = ErrorCode.QUERY_NOT_FOUND.getMessage();
        this.status = ErrorCode.QUERY_NOT_FOUND.getStatus();
    }

    public InvalidParameterException(String message) {
        this.code = ErrorCode.QUERY_NOT_FOUND.getCode();
        this.message = message;
        this.status = ErrorCode.QUERY_NOT_FOUND.getStatus();
    }
}
