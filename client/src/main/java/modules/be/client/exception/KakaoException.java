package modules.be.client.exception;

import org.springframework.http.HttpStatus;

public class KakaoException extends BaseException{
    public KakaoException(String code, String message, int status){
        this.code = code;
        this.message = message;
        switch (status){
            case 204 -> this.status = HttpStatus.NO_CONTENT;
            case 400 -> this.status = HttpStatus.BAD_REQUEST;
            case 401 -> this.status = HttpStatus.UNAUTHORIZED;
            case 403 -> this.status = HttpStatus.FORBIDDEN;
            case 404 -> this.status = HttpStatus.NOT_FOUND;
            case 409 -> this.status = HttpStatus.CONFLICT;
            default -> this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
