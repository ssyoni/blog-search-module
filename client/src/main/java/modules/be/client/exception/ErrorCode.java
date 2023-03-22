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
    NOT_FOUND_HOT_KEYWORD("NOT_FOUND_HOT_KEYWORD", "조회할 수 있는 인기키워드가 없습니다.", HttpStatus.OK),
    INVALID_PAGE_REQUEST("INVALID_PAGE_REQUEST", "요청할 수 있는 페이지 범위 초과", HttpStatus.OK),
    INVALID_PAGE_SIZE_REQUEST("INVALID_PAGE_SIZE_REQUEST", "요청할 수 있는 페이지 사이즈 범위 초과", HttpStatus.OK),
    H2_UNIQUE_CONSTRAIN_ISSUE("INVALID_PAGE_SIZE_REQUEST", "H2 DB 유니크 제약조건으로 인한 중복키 조회 이슈", HttpStatus.OK),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "내부 서버 시스템 에러", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    public String getMessage(){
        return this.message;
    }
}
