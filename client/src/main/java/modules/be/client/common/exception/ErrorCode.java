package modules.be.client.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    UNKNOWN("UNKNOWN","알수 없는 에러", HttpStatus.INTERNAL_SERVER_ERROR),
    QUERY_NOT_FOUND("QUERY_NOT_FOUND","검색어를 입력해주세요", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;   // 응답 상태코드

    public String getMessage(){
        return this.message;
    }
}
