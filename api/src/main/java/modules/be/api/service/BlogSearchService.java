package modules.be.api.service;

import lombok.RequiredArgsConstructor;
import modules.be.domain.event.SearchedEvent;
import modules.be.client.dto.SearchBaseResponse;
import modules.be.client.dto.SearchRequest;
import modules.be.client.factory.RequestClientHandler;
import modules.be.domain.service.SearchWordLogService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Slf4j(topic = "SAVE_SEARCH_WORD_LOGGER")
@Service
@RequiredArgsConstructor
public class BlogSearchService {
    private final RequestClientHandler requestClientHandler;
    private final ApplicationEventPublisher publisher;
    private final SearchWordLogService searchWordLogService;


    @Transactional
    public SearchBaseResponse searchBlog(SearchRequest param){
        SearchBaseResponse response = requestClientHandler.requestToClient(param);
//        searchWordLogService.writeSearchWord(param.getKeyword());
        publisher.publishEvent(new SearchedEvent(param.getKeyword()));
        return response;
    }
}
