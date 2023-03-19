package modules.be.batch.read;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class SearchLogBatchConfiguration {
    public static final int CHUNK_SIZE = 2;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private static final String JOB_NAME = "fileJob";
    private static final String STEP_1_NAME = "getIdStep";
    private static final String MESSAGE = "get Id from search event log ...";

    private Resource outputFileResource = new FileSystemResource("/event-2023-03-20.0.log");

    @Bean
    public Job helloJob() {
        return this.jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer()) // 동일한 파라미터로 재실행할 수 있게 (데이터 갱신)
                .start(getIdStep())
                .build();
    }

    @Bean
    public Step getIdStep() {
        return stepBuilderFactory.get(STEP_1_NAME)
                .tasklet((contribution, chunkContext) -> {
                    log.info("This is HelloStep, {}", MESSAGE);
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
