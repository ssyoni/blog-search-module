package modules.be.domain.service;

import modules.be.domain.dto.SearchWordResponse;
import modules.be.domain.entity.SearchWordLog;
import modules.be.domain.repository.SearchWordLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.Rollback;

import javax.persistence.PersistenceException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchWordLogServiceTest {
    @Autowired
    SearchWordLogService searchWordLogService;
    @Autowired
    SearchWordLogRepository searchWordLogRepository;

    @Test
    public void saveWord(){
        String keyword = "search";

        searchWordLogService.writeSearchWord(keyword);
        SearchWordLog result = searchWordLogRepository.findByKeyword(keyword);

        assertTrue(result != null);
        assertEquals(result.getKeyword(), keyword);
    }

    @Test
    public void updateWord(){
        String keyword = "search";
        int cnt = 4;
        for (int i = 0; i < cnt; i++) {
            searchWordLogService.writeSearchWord(keyword);
        }

        SearchWordLog result = searchWordLogRepository.findByKeyword(keyword);
        System.out.println("result score :: " + result.getScore());

        assertTrue(result != null);
        assertEquals(result.getKeyword(), keyword);
        assertEquals(cnt, result.getScore());
    }

    @Test
    @Rollback(false)
    public void saveWordDuplicate() throws InterruptedException {
        String keyword = "검색어";
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        searchWordLogService.writeSearchWord(keyword);

        for (int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    SearchWordResponse result = searchWordLogService.writeSearchWord(keyword); // 테스트 될 메소드
                    System.out.println("Tid : " + finalI);
                    System.out.println("SCORE : " + result.getScore());
//                    Thread.sleep(1000);
                }catch(Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        latch.await();

        SearchWordLog result = searchWordLogRepository.findByKeyword(keyword);
        assertEquals(numberOfThreads+1, result.getScore());
    }
}