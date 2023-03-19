package modules.be.client.feign.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"modules.be.client"})
public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> requestTemplate.header("Authorization","KakaoAK 383d35930fb99d8d14ff9eb70177444f");
    }

    @Bean
    public FeignClientErrorDecoder feignClientErrorDecoder(){
        return new FeignClientErrorDecoder();
    }

}
