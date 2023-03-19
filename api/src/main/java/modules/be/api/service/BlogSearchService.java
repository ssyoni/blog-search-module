package modules.be.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modules.be.api.dto.BlogSearchRequest;
import modules.be.api.dto.SearchResponse;
import modules.be.api.entity.Blog;
import modules.be.api.entity.Page;
import modules.be.client.feign.KakaoFeignClient;
import modules.be.client.param.ResultParam;
import modules.be.domain.dto.SearchWordResponse;
import modules.be.domain.service.SearchWordLogService;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j(topic = "SAVE_SEARCH_WORD_LOGGER")
@Service
@RequiredArgsConstructor
public class BlogSearchService {
    private final KakaoFeignClient feignClient;
    private final SearchWordLogService searchWordLogService;

    public SearchResponse searchBlog(BlogSearchRequest param){
        ResultParam response = feignClient.request(param.query, param.sort.getCode(), param.page, param.size);

        //TODO Response ok 체크
        writeSearchWord(param.query);
        // page 객체 생성
        Page nextPage = new Page(response.meta().pageable_count,param.size,param.page);

        //Blog list 생성
        List<Blog> blogs = response.documents().stream()
                .map(Blog::new)
                .toList();

        return new SearchResponse(blogs, nextPage);
    }

    public void writeSearchWord(String keyword){
        SearchWordResponse response = searchWordLogService.writeSearchWord(keyword);
        MDC.put("id",response.getId().toString());
        MDC.put("keyword",response.getKeyword());
        MDC.put("score",response.getScore().toString());
        MDC.put("event",response.getEvent());
        log.debug("response {}",response);
        MDC.clear();
    }
}
