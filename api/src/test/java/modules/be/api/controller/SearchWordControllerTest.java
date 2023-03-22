package modules.be.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import modules.be.client.dto.SearchRequest;
import modules.be.client.entity.Sort;
import modules.be.domain.service.SearchWordLogService;
import modules.be.domain.service.SearchWordScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest
class SearchWordControllerTest {
    @Autowired
    SearchWordLogService logService;
    @MockBean
    SearchWordScoreService searchWordScoreService;
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    private SearchRequest createRequest(String keyword, int pageSize, int searchPage, Sort sort){
        return SearchRequest.builder()
                .keyword(keyword)
                .searchPage(searchPage)
                .pageSize(pageSize)
                .pageSort(sort).build();
    }


}