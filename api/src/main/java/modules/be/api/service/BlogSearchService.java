package modules.be.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modules.be.client.dto.SearchBaseResponse;
import modules.be.client.dto.SearchRequest;
import modules.be.client.factory.RequestClientHandler;
import org.springframework.stereotype.Service;

@Slf4j(topic = "SAVE_SEARCH_WORD_LOGGER")
@Service
@RequiredArgsConstructor
public class BlogSearchService {
    private final RequestClientHandler requestClientHandler;

    public SearchBaseResponse searchBlog(SearchRequest param){

        return requestClientHandler.requestToClient(param);
    }
}
