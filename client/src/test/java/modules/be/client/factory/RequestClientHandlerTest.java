package modules.be.client.factory;

import modules.be.client.dto.SearchBaseResponse;
import modules.be.client.dto.SearchRequest;
import modules.be.client.entity.Sort;
import modules.be.client.entity.TargetClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RequestClientHandlerTest {

    @Autowired
    ClientFactory factory;

    private SearchRequest searchRequestMock(){
        return SearchRequest.builder()
                .keyword("카카오뱅크")
                .pageSize(5)
                .pageSort(Sort.RECENCY)
                .searchPage(1)
                .build();
    }


    @Test
    @DisplayName("카카오 클라이언트로 검색어를 조회한다.")
    void 카카오_클라이언트_요청() {
        //given
        TargetClient target = TargetClient.KAKAO;

        //when
        SearchBaseResponse result = factory.create(target).request(searchRequestMock());

        //then
        assertEquals(result.getBlogs().size(), searchRequestMock().getPageSize());  // 요청한 문서 사이즈와 결과 문서 갯수가 같아야한다.
    }

    @Test
    @DisplayName("네이버 클라이언트로 검색어를 조회한다.")
    void 네이버_클라이언트_요청(){
        //given
        TargetClient target = TargetClient.NAVER;

        //when
        SearchBaseResponse result = factory.create(target).request(searchRequestMock());

        //then
        assertEquals(result.getBlogs().size(), searchRequestMock().getPageSize()); // 요청한 문서 사이즈와 결과 문서 갯수가 같아야한다.
    }
}