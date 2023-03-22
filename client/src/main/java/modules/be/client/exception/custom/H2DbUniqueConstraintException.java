package modules.be.client.exception.custom;


import modules.be.client.exception.BaseException;
import modules.be.client.exception.ErrorCode;

public class H2DbUniqueConstraintException extends BaseException {
    public H2DbUniqueConstraintException() {
        this.code = ErrorCode.H2_UNIQUE_CONSTRAIN_ISSUE.getCode();
        this.message = ErrorCode.H2_UNIQUE_CONSTRAIN_ISSUE.getMessage();
        this.status = ErrorCode.H2_UNIQUE_CONSTRAIN_ISSUE.getStatus();
    }
}
