package modules.be.client.factory;

import lombok.RequiredArgsConstructor;
import modules.be.client.dto.SearchBaseResponse;
import modules.be.client.dto.SearchRequest;
import modules.be.client.factory.client.SearchClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.net.SocketTimeoutException;

@Component
@RequiredArgsConstructor
public class RequestClientHandler {
    private final ClientFactory generator;

    public SearchBaseResponse requestToClient(SearchRequest param){
        SearchBaseResponse response = null;
        try {
            // 카카오에게 요청
            SearchClient client = generator.create("KAKAO");
            response = client.request(param);

        }catch (ResourceAccessException e){
            if (e.getCause() instanceof SocketTimeoutException){
                // 타임아웃 시에 네이버에게 요청
                SearchClient client = generator.create("NAVER");
                response = client.request(param);
            }
        }
        return response;
    }
}
