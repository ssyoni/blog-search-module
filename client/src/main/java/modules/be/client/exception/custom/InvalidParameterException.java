package modules.be.client.exception.custom;

import modules.be.client.exception.BaseException;
import modules.be.client.exception.ErrorCode;

public class InvalidParameterException extends BaseException {
    public InvalidParameterException() {
        this.code = ErrorCode.INVALID_PARAMETER_REQUEST.getCode();
        this.message = ErrorCode.INVALID_PARAMETER_REQUEST.getMessage();
        this.status = ErrorCode.INVALID_PARAMETER_REQUEST.getStatus();
    }
}
