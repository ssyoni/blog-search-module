package modules.be.client.common.exception;

import feign.FeignException;
import modules.be.client.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice(basePackages = {"modules.be.api","modules.be.client"})
public class ApiGlobalExceptionHandler {

    @ExceptionHandler
    public ErrorResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex){
        ex.printStackTrace();
        return ErrorResponse.fromException(ex);
    }


}
