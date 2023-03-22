package modules.be.api.controller;

import lombok.RequiredArgsConstructor;
import modules.be.client.common.BaseResponse;
import modules.be.domain.dto.HotKeywordsResponse;
import modules.be.domain.service.SearchWordScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotkeywords")
public class SearchWordController {
    private final SearchWordScoreService searchWordScoreService;
    @GetMapping
    public BaseResponse<HotKeywordsResponse> searchHotKeyword(){
        HotKeywordsResponse response = searchWordScoreService.findAllSearchWordScoreList();
        return new BaseResponse(response);
    }

}