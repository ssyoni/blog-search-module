package modules.be.domain.service;

import lombok.RequiredArgsConstructor;
import modules.be.client.exception.custom.NotFoundHotKeywordExeption;
import modules.be.domain.dto.HotKeywordsResponse;
import modules.be.domain.dto.SearchWordResponse;
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
        SearchWordScore searchWordScore = new SearchWordScore(event.getId(), event.getKeyword(), event.getScore());
        SearchWordScore result = searchWordScoreRepository.save(searchWordScore);
        if (result == null) {
            throw new RuntimeException("searchWordScore save is fail");
        }
    }

    @Transactional
    public HotKeywordsResponse findAllSearchWordScoreList(){
        List<SearchWordScore> searchWordScores = searchWordScoreRepository.findTop10ByOrderByScoreDesc();
        if (searchWordScores.size() < 1) throw new NotFoundHotKeywordExeption();

        List<SearchWordResponse> responses = searchWordScores.stream().map(searchWordScore -> {
            return SearchWordResponse.builder()
                    .keyword(searchWordScore.getKeyword())
                    .score(searchWordScore.getScore()).build();
        }).toList();
        return new HotKeywordsResponse(responses);
    }
}
