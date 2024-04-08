package pe.com.susaya.batch.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import pe.com.susaya.batch.model.Person;
import pe.com.susaya.batch.model.XmlFile;
import pe.com.susaya.batch.steps.PersonItemProccesor;
import pe.com.susaya.batch.steps.PersonItemReader;
import pe.com.susaya.batch.steps.PersonItemWriter;
import pe.com.susaya.batch.steps.PersonMultiItemReader;

import java.io.IOException;

@Configuration
@AllArgsConstructor
public class BatchConfig {


    private final KafkaTemplate<String,String> template;

    private final StreamBridge streamBridge;


    @Bean
    public PersonItemReader itemReader() throws IOException {
        // Obtiene el InputStream del recurso
        return new PersonItemReader();
    }

    @Bean
    public PersonItemProccesor itemProccesor(){
        return new PersonItemProccesor();
    }

    public PersonMultiItemReader multiItemReader() throws IOException {
        return new PersonMultiItemReader(itemReader());
    }

    @Bean
    public PersonItemWriter itemWriter(){
        return new PersonItemWriter();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(5);

        return taskExecutor;
    }



    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws IOException {
        return new StepBuilder("readFile", jobRepository)
                .<XmlFile, Person> chunk(10, transactionManager)
                .reader(multiItemReader())
                .processor(itemProccesor())
                .writer(itemWriter())
                .faultTolerant()
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws IOException {
        return new JobBuilder("readFileWithChunk", jobRepository)
                .start(step(jobRepository,transactionManager))
                .build();
    }



}
