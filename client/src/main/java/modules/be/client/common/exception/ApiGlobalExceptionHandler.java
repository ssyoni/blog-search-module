package modules.be.client.common.exception;

import modules.be.client.common.BaseResponse;
import modules.be.client.common.exception.custom.InvalidParameterException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"modules.be.api","modules.be.client"})
public class ApiGlobalExceptionHandler {

    @ExceptionHandler({
            InvalidParameterException.class,
    })
    private ResponseEntity<BaseResponse> handleCustomException(CustomException e) {
        return new ResponseEntity<>(
                BaseResponse.builder()
                        .code(e.getCode())
                        .message(e.getMessage())
                        .status(e.getStatus())
                        .build(),
                e.getStatus());
    }

}
