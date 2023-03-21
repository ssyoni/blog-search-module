package modules.be.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import modules.be.client.entity.Sort;
import modules.be.api.service.BlogSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BlogSearchControllerTest {

    @Autowired
    BlogSearchService blogSearchService;
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;



    @Test
    public void 블로그_검색_요청() throws Exception {
        String keyword = "";
        int searchPage = 1;
        int pageSize = 10;
        Sort pageSort =  Sort.ACCURACY;

        //given
        mvc.perform(MockMvcRequestBuilders.get("/api/blog/search")
                            .param("keyword",keyword)
                            .param("searchPage", String.valueOf(searchPage))
                            .param("pageSize", String.valueOf(pageSize))
                            .param("pageSort", String.valueOf(pageSort))
        ).andExpect(status().isOk());



       /* mvc.perform(get("/v1/orders/order-number")
                        .header("Authorization", "Bearer " + anonymToken)
                        .contentType(MediaType.TEXT_HTML)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-order-number",
                        getDocumentRequest(),
                        getDocumentResponse(),터
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING).description("결과"),
                                fieldWithPath("data.orderNo").type(JsonFieldType.STRING).description("주문번호")
                        )
                ));*/
    }

}