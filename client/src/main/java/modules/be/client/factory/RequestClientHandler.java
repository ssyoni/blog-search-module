package modules.be.client.factory;

import lombok.RequiredArgsConstructor;
import modules.be.client.dto.SearchBaseResponse;
import modules.be.client.dto.SearchRequest;
import modules.be.client.entity.TargetClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.net.SocketTimeoutException;

@Component
@RequiredArgsConstructor
public class RequestClientHandler {
    private final ClientFactory factory;

    public SearchBaseResponse requestToClient(SearchRequest param){
        SearchBaseResponse response = null;

        try {
            // 카카오 인스턴스 생성 후 요청
            response = factory.create(TargetClient.KAKAO).request(param);

        }catch (ResourceAccessException e){
            if (e.getCause() instanceof SocketTimeoutException){
                // 타임아웃 시에 네이버에게 요청
                response = factory.create(TargetClient.NAVER).request(param);
            }
        }
        return response;
    }

}
