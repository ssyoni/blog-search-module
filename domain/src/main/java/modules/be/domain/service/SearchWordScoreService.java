package modules.be.domain.service;

import lombok.RequiredArgsConstructor;
import modules.be.domain.entity.SearchWordScore;
import modules.be.domain.event.UpdatedKeywordEvent;
import modules.be.domain.repository.SearchWordScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchWordScoreService {
    private final SearchWordScoreRepository searchWordScoreRepository;

    @Transactional
    public void saveSearchWord(UpdatedKeywordEvent event){
        // TODO 예외처리
        searchWordScoreRepository.save(new SearchWordScore(event.getId(), event.getKeyword(), event.getScore()));
    }
}
