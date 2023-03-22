package modules.be.domain.service;

import modules.be.domain.dto.SearchWordResponse;
import modules.be.domain.entity.SearchWordLog;
import modules.be.domain.entity.SearchWordScore;
import modules.be.domain.repository.SearchWordLogRepository;
import modules.be.domain.repository.SearchWordScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.Rollback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class SearchWordLogServiceTest {
    @Autowired
    SearchWordLogService searchWordLogService;
    @Autowired
    SearchWordLogRepository searchWordLogRepository;
    @Autowired
    SearchWordScoreRepository searchWordScoreRepository;

    @Test
    @DisplayName("동시에 10명이 하나의 트랜잭션에 접근했을 때 비관적 락 적용해서 저장 모두 성공시키기")
    public void 키워드저장_동시성_테스트() throws InterruptedException {
        String keyword = "동시성 테스트";
        int numberOfThreads = 5;
        ExecutorService service = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        searchWordLogService.writeSearchWord(keyword);

        for (int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    SearchWordResponse result = searchWordLogService.writeSearchWord(keyword); // 테스트 될 메소드
                    System.out.println("Tid : " + finalI);
                    System.out.println("SCORE : " + result.getScore());
                }catch(ObjectOptimisticLockingFailureException e) {
                    System.out.println("충돌 감지 !!");
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        latch.await();

        SearchWordLog result = searchWordLogRepository.findByKeyword(keyword);
        assertEquals(numberOfThreads+1, result.getScore());
    }
    @Test
    @DisplayName("로그테이블에 검색어가 없으면 검색어를 저장한다.")
    @Rollback
    public void 검색어_저장_테스트(){
        //given
        String keyword = "new 검색어";
        searchWordLogService.writeSearchWord(keyword);

        //when
        SearchWordLog result = searchWordLogService.findByKeyword(keyword);

        //then
        assertTrue(result != null);
        assertEquals(result.getKeyword(), keyword);
        assertEquals(result.getScore(),1);
    }

    @Test
    @DisplayName("검색어를 조회하면 score가 갱신된다.")
    @Rollback
    public void 검색어_조회시_score_업데이트_테스트(){
        //given
        String keyword = "새로운 검색어";
        SearchWordResponse before = searchWordLogService.writeSearchWord(keyword);

        //when
        SearchWordResponse result = searchWordLogService.writeSearchWord(keyword);

        //then
        assertEquals(before.getScore(),1);
        assertEquals(result.getScore(),2);
    }

    @Test
    @DisplayName("검색어 쓰기작업이 일어나면 검색어 읽기 테이블이 갱신된다.")
    public void 검색어_저장시_읽기테이블_갱신_테스트(){
        //given
        String keyword = "카카오뱅크";

        //when
        SearchWordResponse response = searchWordLogService.writeSearchWord(keyword); // 쓰기 테이블 조회
        SearchWordScore score = searchWordScoreRepository.findByKeyword(keyword);   // 읽기 테이블 조회

        //then
        assertEquals(response.getKeyword(), score.getKeyword());
        assertEquals(response.getScore(), score.getScore());
    }

    @Test
    @DisplayName("검색어 쓰기작업이 실패하면 검색어 읽기갱신 작업도 실패한다.")
    public void 검색어_쓰기_실패시_읽기갱신작업_롤백(){
        //given
        String keyword = "카카오뱅크";



    }



}