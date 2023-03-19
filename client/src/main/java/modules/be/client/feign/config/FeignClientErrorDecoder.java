package modules.be.client.feign.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import modules.be.client.common.exception.CommonException;
import modules.be.client.common.exception.ErrorCode;

public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
                return new CommonException(ErrorCode.UNKNOWN,"query parameter is null");
            case 404:
                if(methodKey.contains("getOrders")){
                    return new CommonException(ErrorCode.UNKNOWN,"feign error!!!!");
                }
                break;
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
