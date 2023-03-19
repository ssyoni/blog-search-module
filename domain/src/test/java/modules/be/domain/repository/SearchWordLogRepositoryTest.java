package modules.be.domain.repository;

import modules.be.domain.entity.SearchWordLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchWordLogRepositoryTest {
    @Autowired
    SearchWordLogRepository searchWordLogRepository;

    /*@Test
    public void saveWordTest() throws InterruptedException {
        String keyword = "검색어";
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        SearchWordLog searchWordLog = new SearchWordLog(keyword);
        for (int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    searchWordLogRepository.save(searchWordLog); // 테스트 될 메소드
                    System.out.println("Tid : " + finalI);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        latch.await();

        SearchWordLog result = searchWordLogRepository.findByKeywordLock(keyword);
        assertEquals(numberOfThreads, result.getScore());
    }*/
}