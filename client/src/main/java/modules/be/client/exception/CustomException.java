package modules.be.client.exception;

import org.springframework.http.HttpStatus;

public interface CustomException {

    String getCode();
    String getMessage();
    HttpStatus getStatus();
}