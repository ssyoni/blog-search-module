package modules.be.client.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import modules.be.client.common.exception.CommonException;
import modules.be.client.common.exception.ErrorCode;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse{
    private String code;
    private String message;
    public boolean isSuccess = false;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse fromException(Exception ex) {
        if (ex instanceof CommonException commonException) {
            return new ErrorResponse(commonException.getErrorCode().getCode(), commonException.getErrorCode().getMessage());
        }
        return new ErrorResponse(ErrorCode.UNKNOWN.getCode(), ErrorCode.UNKNOWN.getMessage());
    }

    public String toJsonString() {
        return String.format("{\"code\": \"%s\", \"message\": \"%s\", \"isSuccess\": \"%s\"}",
                code, message, isSuccess);
    }
}
