package modules.be.client.factory.client;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import modules.be.client.dto.*;
import modules.be.client.entity.BlogInfo;
import modules.be.client.entity.Pagination;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoClient implements SearchClient {
    @Value("${open-api.kakao.url}")
    private static String URL;

    @Value("${open-api.kakao.api-key}")
    private static String API_KEY;
    private final RestTemplate restTemplate;

    public SearchBaseResponse request(SearchRequest requestParam) {
        // 요청처리
        KakaoRequest request = new KakaoRequest(requestParam);

        HttpEntity<String> httpEntity = new HttpEntity<>(createHeader());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("query", request.getQuery())
                .queryParam("sort", request.getSort())
                .queryParam("page", request.getPage())
                .queryParam("size", request.getSize())
                .build()
                .encode()
                .toUri();

        //요청
        ResponseEntity<KakaoResponse> result = restTemplate.exchange(uri,HttpMethod.GET,httpEntity, KakaoResponse.class);


        //응답처리
        List<BlogInfo> blogs = result.getBody().documents.stream().map(document -> {
            String datetime = document.datetime.withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return BlogInfo.builder()
                    .blogname(document.blogname)
                    .contents(document.contents)
                    .title(document.title)
                    .url(document.url)
                    .thumbnail(document.thumbnail)
                    .date(LocalDate.parse(datetime))
                    .build();
            }).toList();

        Pagination pagination = Pagination.builder()
                        .totalCount(result.getBody().meta.pageable_count)
                        .size(requestParam.getPageSize())
                        .currentPage(requestParam.getSearchPage())
                        .build();

        return SearchBaseResponse.builder()
                .blogs(blogs)
                .page(pagination)
                .build();
    }
    @Override
    public HttpHeaders createHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.VARY, HttpHeaders.ACCEPT_ENCODING);
        httpHeaders.add("Authorization", API_KEY);
        return httpHeaders;
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class KakaoResponse{
        private List<Document> documents;
        private Meta meta;
    }

    @Getter
    @Setter
    private static class Document{
        private String title;
        private String contents;
        private String url;
        private String blogname;
        private String thumbnail;
        private ZonedDateTime datetime;
    }

    @Getter
    @Setter
    public static class Meta{
        private int total_count;
        private int pageable_count;
        private boolean is_end;
    }


}
