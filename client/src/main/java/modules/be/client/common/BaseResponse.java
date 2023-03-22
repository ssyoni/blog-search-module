package modules.be.client.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponse<T>{
    private String code;        // 응답 코드
    private int status;         // 응답 HTTP 상태
    public T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;     // 응답 메세지

    @Builder
    public BaseResponse(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status.value();
    }
    @Builder
    public BaseResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }

    @Builder
    public BaseResponse(T obj) {
        this.code = ResponseCode.OK.getCode();
        this.data = obj;
        this.status = ResponseCode.OK.getStatus().value();
    }

}
