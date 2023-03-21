package modules.be.domain.service;

import lombok.RequiredArgsConstructor;
import modules.be.domain.dto.HotKeywordsResponse;
import modules.be.domain.dto.SearchWordResponse;
import modules.be.domain.entity.SearchWordLog;
import modules.be.domain.entity.SearchWordScore;
import modules.be.domain.event.UpdatedKeywordEvent;
import modules.be.domain.repository.SearchWordScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchWordScoreService {
    private final SearchWordScoreRepository searchWordScoreRepository;

    @Transactional
    public void saveSearchWord(UpdatedKeywordEvent event){
        // TODO 예외처리
        searchWordScoreRepository.save(new SearchWordScore(event.getId(), event.getKeyword(), event.getScore()));
    }

    @Transactional
    public HotKeywordsResponse findAllSearchWordScoreList(){
        // TODO 예외처리
        List<SearchWordScore> searchWordScores = searchWordScoreRepository.findTop10ByOrderByScoreDesc();
        List<SearchWordResponse> responses = searchWordScores.stream().map(searchWordScore -> {
            return SearchWordResponse.builder()
                    .keyword(searchWordScore.getKeyword())
                    .score(searchWordScore.getScore()).build();
        }).toList();
        return new HotKeywordsResponse(responses);
    }
}
