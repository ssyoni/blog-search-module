package modules.be.client.factory.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import modules.be.client.dto.KakaoRequest;
import modules.be.client.dto.SearchBaseResponse;
import modules.be.client.dto.NaverRequest;
import modules.be.client.dto.SearchRequest;
import modules.be.client.entity.BlogInfo;
import modules.be.client.entity.Pagination;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NaverClient implements SearchClient{

    private final String URL = "https://openapi.naver.com/v1/search/blog.json";
    private final String CLIENT_ID = "1mZHo66nce25UyYq7yDh";
    private final String SECRET = "HNA7wdjufb";

    private final RestTemplate restTemplate;

    @Override
    public SearchBaseResponse request(SearchRequest requestParam) {
        // 요청처리
        NaverRequest request = new NaverRequest(requestParam);
        HttpEntity<String> httpEntity = new HttpEntity<>(createHeader());

        //요청
        ResponseEntity<NaverResponse> result = restTemplate.exchange(createUri(request), HttpMethod.GET,httpEntity, NaverResponse.class);

        List<BlogInfo> blogs = result.getBody().items.stream().map(item -> {
            return BlogInfo.builder()
                    .blogname(item.bloggername)
                    .contents(item.description)
                    .title(item.title)
                    .url(item.bloggerlink)
                    .thumbnail(item.link)
                    .date(LocalDate.parse(item.postdate,DateTimeFormatter.ofPattern("yyyyMMdd")))
                    .build();
        }).toList();

        Pagination pagination = Pagination.builder()
                .totalCount(result.getBody().total)
                .size(requestParam.getPageSize())
                .currentPage(result.getBody().start) // 마지막 페이지면 현재 페이지 반환, 아니면 다음 페이지
                .build();

        return SearchBaseResponse.builder()
                .blogs(blogs)
                .page(pagination)
                .build();
    }

    public URI uriBuilder_(KakaoRequest request) {
        URI uri = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("query", request.getQuery())
                .queryParam("sort", request.getSort())
                .queryParam("page", request.getPage())
                .queryParam("size", request.getSize())
                .build()
                .encode()
                .toUri();
        return uri;
    }

    @Override
    public HttpHeaders createHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add("X-Naver-Client-Id", CLIENT_ID);
        httpHeaders.add("X-Naver-Client-Secret", SECRET);
        return httpHeaders;
    }

    private URI createUri(NaverRequest request){
        return UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("query", request.getQuery())
                .queryParam("sort", request.getSort())
                .queryParam("start", request.getStart())
                .queryParam("display", request.getDisplay())
                .build()
                .encode()
                .toUri();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class NaverResponse{
        private List<Item> items;
        private int total;
        private int start;
        private int display;
    }

    @Getter
    @Setter
    private static class Item{
        private String title;
        private String description;
        private String bloggerlink;
        private String bloggername;
        private String link;
        private String postdate;
    }


}
