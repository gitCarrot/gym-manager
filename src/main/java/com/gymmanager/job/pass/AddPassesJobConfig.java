package com.gymmanager.job.pass;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddPassesJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final AddPassesJobTasklet addPassesJobTasklet;


    public AddPassesJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, AddPassesJobTasklet addPassesJobTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.addPassesJobTasklet = addPassesJobTasklet;
    }

    @Bean
    public Job addPassesJob(){
        return this.jobBuilderFactory.get("addPassesJob")
                .start(addPassesStep())
                .build();
    }

    @Bean
    public Step addPassesStep() {
        return this.stepBuilderFactory.get("addPassesStep")
                .tasklet(addPassesJobTasklet)
                .build();
    }


}
