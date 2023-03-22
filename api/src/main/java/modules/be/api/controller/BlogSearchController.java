package modules.be.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modules.be.api.service.BlogSearchService;
import modules.be.client.common.BaseResponse;
import modules.be.client.dto.SearchBaseResponse;
import modules.be.client.dto.SearchRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog")
public class BlogSearchController {
    private final BlogSearchService blogSearchService;

    @PostMapping("/search")
    public BaseResponse<SearchBaseResponse> searchBlog(@Valid @RequestBody SearchRequest searchRequest
    ){
        log.info("blog search requestBody = {}",searchRequest.toString());
        SearchBaseResponse response = blogSearchService.searchBlog(searchRequest);
        return new BaseResponse(response);
    }
}
