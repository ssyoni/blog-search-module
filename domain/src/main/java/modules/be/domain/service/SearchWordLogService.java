package modules.be.domain.service;

import lombok.RequiredArgsConstructor;
import modules.be.domain.dto.HotKeywordsResponse;
import modules.be.domain.dto.SearchWordResponse;
import modules.be.domain.entity.SearchWordLog;
import modules.be.domain.entity.SearchWordScore;
import modules.be.domain.event.UpdatedKeywordEvent;
import modules.be.domain.repository.SearchWordLogRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchWordLogService {
    private final SearchWordLogRepository searchWordLogRepository;
    private final ApplicationEventPublisher publisher;

    public SearchWordLog findByKeyword(String keyword){
        return searchWordLogRepository.findByKeyword(keyword);
    }

    /* 검색어 쓰기작업 */
    @Transactional
    public SearchWordResponse writeSearchWord(String keyword){
        SearchWordLog searchWordLog = findByKeyword(keyword);
        boolean isWordExist = searchWordLog != null;
        // update
        if (isWordExist){
            searchWordLog.updateScore();
        }
        // insert
        else{
            searchWordLog = searchWordLogRepository.save(new SearchWordLog(keyword));
        }

        // 검색어 쓰기작업이 일어나면 검색조회 테이블을 갱신한다.
        publisher.publishEvent(new UpdatedKeywordEvent(searchWordLog.getId(), searchWordLog.getKeyword(), searchWordLog.getScore()));
        return new SearchWordResponse(searchWordLog);
    }

}
