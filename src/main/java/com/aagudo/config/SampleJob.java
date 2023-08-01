package com.aagudo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aagudo.listener.FirstJobListener;
import com.aagudo.service.FirstTasklet;
import com.aagudo.service.SecondTasklet;

@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private FirstTasklet firstTasklet;
	
	@Autowired
	private SecondTasklet secondTasklet;
	
	@Autowired
	private FirstJobListener firstJobListener;
	
	@Autowired
	private StepExecutionListener stepExecutionListener;
	
	@Bean
	public Job firstJob() {
		return this.jobBuilderFactory.get("First Job")
			.incrementer(new RunIdIncrementer())
			.start(firstStep())
			.next(secondStep())
			.listener(this.firstJobListener)
			.build();
			
	}
	
	private Step firstStep() {
		return this.stepBuilderFactory.get("First Step")
			.tasklet(this.firstTasklet)
			.build();
		
	}
	
	private Step secondStep() {
		return this.stepBuilderFactory.get("Second Step")
				.listener(this.stepExecutionListener)
				.tasklet(this.secondTasklet)
				.build();
	}
	

}
