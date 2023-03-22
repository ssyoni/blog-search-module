package modules.be.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import modules.be.api.service.BlogSearchService;
import modules.be.client.dto.SearchRequest;
import modules.be.client.entity.Sort;
import modules.be.domain.repository.SearchWordLogRepository;
import modules.be.domain.repository.SearchWordScoreRepository;
import modules.be.domain.service.SearchWordLogService;
import modules.be.domain.service.SearchWordScoreService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class BlogSearchControllerTest {
    @MockBean
    BlogSearchService blogSearchService;
    @MockBean
    SearchWordLogService logService;
    @MockBean
    SearchWordScoreService scoreService;
    @MockBean
    SearchWordLogRepository logRepository;
    @MockBean
    SearchWordScoreRepository scoreRepository;
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

    @Test
    public void 블로그_검색_요청() throws Exception {
        //when
        SearchRequest request = createRequest("카카오뱅크",5,1,Sort.ACCURACY);

        //given
        mvc.perform(MockMvcRequestBuilders.post("/api/blog/search")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}