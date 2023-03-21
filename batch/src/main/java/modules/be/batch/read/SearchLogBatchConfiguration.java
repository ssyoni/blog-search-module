package modules.be.batch.read;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import modules.be.batch.entity.EventLog;
import modules.be.consumer.entity.SearchWordScore;
import modules.be.consumer.service.SearchWordScoreService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class SearchLogBatchConfiguration {
    private final SearchWordScoreService searchWordScoreService;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private static final String JOB_NAME = "fileJob";
    private static final String STEP_1_NAME = "readEventLogStep";
    private static final String STEP_2_NAME = "insertStep";
    private static final String STEP_3_NAME = "updateStep";
    private static final String STEP_4_NAME = "deleteStep";

    private final String DIR = "logs/batch";
    private String[] filenames;
    private List<EventLog> eventLogs = new ArrayList<>();

    @Bean
    public Job fileJob() {
        return this.jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer()) // 동일한 파라미터로 재실행할 수 있게 (데이터 갱신)
                .start(readEventLogStep())
                .next(insertDB())
                .next(updateDB())
                .next(deleteFile())
                .build();
    }


    @Bean
    public Step readEventLogStep(){
        return stepBuilderFactory.get(STEP_1_NAME)
                .tasklet((contribution, chunkContext) -> {
                    // batch 디렉토리 아래에 있는 파일들 읽기
                    File dir = new File(DIR);
                    filenames = dir.list();
                    String filePath = "";

                    // batch 하위 로그 파일들 읽어서 객체로 만들기
                    for (String filename:filenames) {
                        filePath = DIR + "/" + filename;
                        BufferedReader reader = new BufferedReader(new FileReader(filePath));
                        String line = reader.readLine();
                        while (line != null) {
                            ObjectMapper mapper = new ObjectMapper();
                            EventLog eventLog = mapper.readValue(line, EventLog.class);
                            eventLogs.add(eventLog);
                            line = reader.readLine();
                        }
                    }
                    return RepeatStatus.FINISHED;
                }).build();
    }


    @Bean
    public Step insertDB(){
        return stepBuilderFactory.get(STEP_2_NAME)
                .tasklet((contribution, chunkContext) -> {
                    log.info("======= Insert event to SearchEvent ======");
                    eventLogs.stream()
                            .forEach(event -> {
                                if (event.getEvent().equals("insert")){
                                    SearchWordScore result = searchWordScoreService.saveEntity(
                                            new SearchWordScore(event.getId(), event.getKeyword(), Long.parseLong(event.getScore())));
                                    log.info("insert result , keyword = {}, id = {}",result.getKeyword(), result.getId());
                                }
                            });
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step updateDB(){
        return stepBuilderFactory.get(STEP_3_NAME)
                .tasklet((contribution, chunkContext) -> {
                    log.info("======= Update event to SearchEvent ======");
                    eventLogs.stream()
                            .forEach(event -> {
                                if (event.getEvent().equals("update")){
                                    SearchWordScore result = searchWordScoreService.updateScore(event.getId(),event.getScore());
                                    log.info("update result , {}",result.getScore());
                                }
                            });
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step deleteFile(){
        return stepBuilderFactory.get(STEP_4_NAME)
                .tasklet((contribution, chunkContext) -> {
                    // 배치작업 끝나면 batch 디렉토리 파일들 전부 삭제
                    log.info("============ delete files ===========");
                    for (String filename : filenames) {
                        File file = new File(DIR + "/" + filename);
                        file.delete();
                    }
                    return RepeatStatus.FINISHED;
                }).build();
    }
}