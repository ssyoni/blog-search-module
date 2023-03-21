package modules.be.consumer.service;

import lombok.RequiredArgsConstructor;
import modules.be.consumer.entity.SearchWordScore;
import modules.be.consumer.repository.SearchWordScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchWordScoreService {

    private final SearchWordScoreRepository searchWordScoreRepository;

    @Transactional
    public SearchWordScore saveEntity(SearchWordScore entity){
        return searchWordScoreRepository.save(entity);
    }

    public SearchWordScore findById(String id){
        // TODO 예외처리
        return searchWordScoreRepository.findById(UUID.fromString(id)).orElseThrow();
    }

    @Transactional
    public SearchWordScore updateScore(String id, String score){
        SearchWordScore searchWordScore = findById(id);
        searchWordScore.updateScore(Long.valueOf(score));

        return searchWordScore;
    }
}
