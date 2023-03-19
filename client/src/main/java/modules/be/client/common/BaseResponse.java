package modules.be.client.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import modules.be.client.param.ResultParam;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BaseResponse<T> extends ErrorResponse {
    public T data;

    public BaseResponse(T response) {
        this.data = response;
    }
}
