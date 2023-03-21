package modules.be.api.controller;

import lombok.RequiredArgsConstructor;
import modules.be.client.common.BaseResponse;
import modules.be.domain.dto.HotKeywordsResponse;
import modules.be.domain.dto.SearchWordResponse;
import modules.be.domain.service.SearchWordLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/hotkeyword")
public class SearchWordController {

    private final SearchWordLogService searchWordLogService;
    @GetMapping
    public BaseResponse<HotKeywordsResponse> searchHotKeyword(){
        HotKeywordsResponse response = searchWordLogService.selectSearchWordList();
        return new BaseResponse(response);
    }

}

