package modules.be.client.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    UNKNOWN("UNKNOWN","알수 없는 에러", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_PARAMETER_REQUEST("INVALID_PARAMETER_REQUEST","잘못된 파라미터 입력", HttpStatus.BAD_REQUEST),
    NOT_FOUND_KEYWORD("NOT_FOUND_KEYWORD", "존재하지 않는 키워드", HttpStatus.OK),
    NOT_FOUND_HOT_KEYWORD("NOT_FOUND_KEYWORD", "존재하지 않는 키워드", HttpStatus.OK),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "내부 서버 시스템 에러", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    public String getMessage(){
        return this.message;
    }
}
