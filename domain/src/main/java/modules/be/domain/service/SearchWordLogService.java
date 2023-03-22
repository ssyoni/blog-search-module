package modules.be.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modules.be.client.exception.custom.H2DbUniqueConstraintException;
import modules.be.client.exception.custom.NotFoundKeywordException;
import modules.be.domain.dto.HotKeywordsResponse;
import modules.be.domain.dto.SearchWordResponse;
import modules.be.domain.entity.SearchWordLog;
import modules.be.domain.entity.SearchWordScore;
import modules.be.domain.event.UpdatedKeywordEvent;
import modules.be.domain.repository.SearchWordLogRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchWordLogService {
    private final SearchWordLogRepository searchWordLogRepository;
    private final ApplicationEventPublisher publisher;

    public SearchWordLog findByKeyword(String keyword){
        SearchWordLog searchWordLog = searchWordLogRepository.findByKeyword(keyword);
        return searchWordLog;
    }

    /* 검색어 쓰기작업 */
    @Transactional
    public SearchWordResponse writeSearchWord(String keyword){
        SearchWordLog searchWordLog = searchWordLogRepository.findByKeywordForUpdate(keyword);
        SearchWordLog result = null;
        try {
            result = upsertSearchWord(searchWordLog, keyword);
        }catch(Exception e){
            log.debug("H2 DB 유니크 제약조건으로 인한 중복키 조회 이슈. 검색 키워드 = {}",keyword);
            throw new H2DbUniqueConstraintException();
        }


        // 검색어 쓰기작업이 일어나면 검색조회 테이블을 갱신한다.
        publisher.publishEvent(new UpdatedKeywordEvent(result.getId(), result.getKeyword(), result.getScore()));
        return new SearchWordResponse(result);
    }

    private SearchWordLog upsertSearchWord(SearchWordLog searchWordLog, String keyword){
        boolean isWordExist = searchWordLog != null;
        // update
        if (isWordExist){
            searchWordLog.updateScore();
        }
        // insert
        else{
            searchWordLog = searchWordLogRepository.save(new SearchWordLog(keyword));
        }
        return searchWordLog;
    }

}