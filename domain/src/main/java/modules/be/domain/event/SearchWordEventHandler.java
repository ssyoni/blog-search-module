package modules.be.domain.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modules.be.domain.service.SearchWordLogService;
import modules.be.domain.service.SearchWordScoreService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchWordEventHandler {
    private final SearchWordLogService logService;
    private final SearchWordScoreService scoreService;

    /* 검색어를 입력하는 이벤트가 발생하면 검색어를 저장한다. */
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async
    public void writeSearchWord(SearchedEvent event){
        log.info("create SearchWordEventHandler event = {}",event.toString());
        logService.writeSearchWord(event.getKeyword());
    }

    /* 검색어 저장되는 이벤트가 발생하면 조회테이블을 갱신한다. */
    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async
    public void writeSearchWordScore(UpdatedKeywordEvent event){
        log.info("create SearchWordEventHandler event ={}", event.toString());
        scoreService.saveSearchWord(event);
    }
}
