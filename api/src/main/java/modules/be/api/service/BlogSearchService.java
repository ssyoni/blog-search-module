package modules.be.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modules.be.client.dto.SearchBaseResponse;
import modules.be.client.dto.SearchRequest;
import modules.be.client.factory.RequestClientHandler;
import modules.be.domain.event.SearchedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogSearchService {
    private final RequestClientHandler requestClientHandler;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public SearchBaseResponse searchBlog(SearchRequest param){
        SearchBaseResponse response = requestClientHandler.requestToClient(param);
        publisher.publishEvent(new SearchedEvent(param.getKeyword()));
        return response;
    }
}
