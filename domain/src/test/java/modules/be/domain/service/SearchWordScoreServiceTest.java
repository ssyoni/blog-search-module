package modules.be.domain.service;

import modules.be.client.exception.custom.NotFoundHotKeywordExeption;
import modules.be.domain.entity.SearchWordScore;
import modules.be.domain.event.UpdatedKeywordEvent;
import modules.be.domain.repository.SearchWordScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class SearchWordScoreServiceTest {

    @InjectMocks
    SearchWordScoreService searchWordScoreService;

    @Mock
    SearchWordScoreRepository searchWordScoreRepository;

    private UUID uuid = UUID.randomUUID();


    private UpdatedKeywordEvent updatedKeywordEventMock(){
        return new UpdatedKeywordEvent(uuid,"카카오뱅크",10L);
    }

    @Test
    @DisplayName("검색 이벤트를 조회테이블에 저장하고 null을 반환하면 runtimeException 예외가 발생한다.")
    public void 검색_이벤트_조회테이블에_저장시_실패(){
        //given
        UpdatedKeywordEvent event = updatedKeywordEventMock();
        SearchWordScore searchWordScore = new SearchWordScore(event.getId(), event.getKeyword(), event.getScore());

        //when
        given(searchWordScoreRepository.save(searchWordScore)).willReturn(isNull());

        //then
        Assertions.assertThrows(RuntimeException.class, () -> {
            searchWordScoreService.saveSearchWord(event);
        });
    }

    @Test
    @DisplayName("인기순 정렬 Top 10 키워드 검색 결과가 0개일시 NotFoundHotKeywordExeption 예외 발생")
    public void 인기검색어_조회결과_0_이면_예외처리(){
        // when
        List<SearchWordScore> searchWordScoreList = new ArrayList<>();
        given(searchWordScoreRepository.findTop10ByOrderByScoreDesc()).willReturn(searchWordScoreList);

        //then
        Assertions.assertThrows(NotFoundHotKeywordExeption.class, () -> {
            searchWordScoreService.findAllSearchWordScoreList();
        });
    }




}