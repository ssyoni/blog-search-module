package modules.be.client.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    OK("DONE", "성공",HttpStatus.OK);

    private final String code;         // 응답 코드
    private final String message;      // 응답 메세지
    private final HttpStatus status;   // 응답 상태코드

}
