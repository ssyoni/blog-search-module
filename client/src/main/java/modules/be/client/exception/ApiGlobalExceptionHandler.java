package modules.be.client.exception;

import modules.be.client.common.BaseResponse;
import modules.be.client.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"modules.be.api","modules.be.client"})
public class ApiGlobalExceptionHandler {

    @ExceptionHandler({
            InvalidParameterException.class,
            NotFoundKeywordException.class,
            NotFoundHotKeywordExeption.class,
            InvalidPageSizeRequestException.class,
            H2DbUniqueConstraintException.class
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<BaseResponse> handleMethodArgumentValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                BaseResponse.builder()
                        .message(ErrorCode.INVALID_PARAMETER_REQUEST.getMessage())
                        .status(ErrorCode.INVALID_PARAMETER_REQUEST.getStatus())
                        .build(),
                HttpStatus.OK);
    }

}
