package modules.be.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modules.be.api.dto.BlogSearchRequest;
import modules.be.api.dto.SearchResponse;
import modules.be.api.service.BlogSearchService;
import modules.be.client.common.BaseResponse;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j()
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog")
public class BlogSearchController {
    private final BlogSearchService blogSearchService;

    @GetMapping("/search")
    public BaseResponse<SearchResponse> searchBlog(@RequestBody BlogSearchRequest param){
        log.info("searchBlog BlogSearchRequestParam query={}, sort={}, size={}, offset={}",param.query, param.sort, param.size, param.page);
        SearchResponse response = blogSearchService.searchBlog(param);

        return new BaseResponse<>(response);
    }
}
