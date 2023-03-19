package modules.be.client.feign;

import modules.be.client.param.ResultParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenFeignClient", url = "${open-api.kakao.url}")
public interface KakaoFeignClient {

    @GetMapping
    ResultParam request(@RequestParam(required = true) String query,
                     @RequestParam(required = false) String sort,
                     @RequestParam(required = false) String page,
                     @RequestParam(required = false) String size);
}
