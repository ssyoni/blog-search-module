package modules.be.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modules.be.client.common.exception.ErrorCode;
import modules.be.client.dto.SearchBaseResponse;
import modules.be.client.entity.Sort;
import modules.be.client.dto.SearchRequest;
import modules.be.api.service.BlogSearchService;
import modules.be.client.common.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog")
public class BlogSearchController {
    private final BlogSearchService blogSearchService;

    @PostMapping("/search")
    public BaseResponse<SearchBaseResponse> searchBlog(@RequestBody SearchRequest searchRequest
    ){
        log.info("blog search requestBody = {}",searchRequest.toString());
        SearchBaseResponse response = blogSearchService.searchBlog(searchRequest);
        return new BaseResponse(response);
    }
}
