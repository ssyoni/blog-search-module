package modules.be.api.service;

import modules.be.client.dto.SearchRequest;
import modules.be.client.entity.Sort;
import modules.be.domain.entity.SearchWordLog;
import modules.be.domain.service.SearchWordLogService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.RecordApplicationEvents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RecordApplicationEvents
class BlogSearchServiceTest {
    @Autowired
    BlogSearchService blogSearchService;
    @Autowired
    SearchWordLogService searchWordLogService;

    private SearchRequest createRequest(String keyword, String pageSize, String searchPage, Sort sort){
        return SearchRequest.builder()
                .keyword(keyword)
                .searchPage(searchPage)
                .pageSize(pageSize)
                .pageSort(sort).build();
    }

    @Test
    @DisplayName("검색어를 조회하면 검색어가 테이블에 저장된다.")
    void 검색어_저장_이벤트_테스트() {
        //given
        String keyword = "카카오뱅크";
        SearchRequest request = createRequest(keyword, "5", "1", Sort.ACCURACY);

        //when
        blogSearchService.searchBlog(request);
        SearchWordLog result = searchWordLogService.findByKeyword(keyword);

        //then
        assertTrue(result != null);
        assertEquals(result.getKeyword(),keyword);
    }
}