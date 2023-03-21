package modules.be.client.factory;

import lombok.RequiredArgsConstructor;
import modules.be.client.factory.client.KakaoClient;
import modules.be.client.factory.client.NaverClient;
import modules.be.client.factory.client.SearchClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ClientFactory {

    private final RestTemplate restTemplate;
    public SearchClient create(String type){
        SearchClient client = null;
        switch (type){
            case "KAKAO":
                client =  new KakaoClient(restTemplate);
                break;
            case "NAVER":
                client =  new NaverClient(restTemplate);
                break;
        }
        return client;
    }

}
