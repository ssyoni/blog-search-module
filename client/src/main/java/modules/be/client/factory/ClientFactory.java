package modules.be.client.factory;

import lombok.RequiredArgsConstructor;
import modules.be.client.entity.TargetClient;
import modules.be.client.factory.client.KakaoClient;
import modules.be.client.factory.client.NaverClient;
import modules.be.client.factory.client.SearchClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ClientFactory {

    private final RestTemplate restTemplate;
    public SearchClient create(TargetClient target){
        switch (target){
            case KAKAO:
                return new KakaoClient(restTemplate);
            case NAVER:
                return new NaverClient(restTemplate);
        }
        return null;
    }

}
