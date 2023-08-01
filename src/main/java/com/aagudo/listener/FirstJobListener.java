package com.aagudo.listener;

import java.util.Arrays;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		jobExecution.getExecutionContext().put("lista", Arrays.asList(1,2,3,4,5));
		System.out.println("calling the job: " + jobExecution.getJobInstance().getJobName() 
				+ " with id: " + jobExecution.getJobId() + "with Parameters: " 
				+ jobExecution.getExecutionContext());
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("Finishing the job" + jobExecution.getJobInstance().getJobName() 
				+ "with id: " + jobExecution.getJobId() + "with Parameters: " 
				+ jobExecution.getJobParameters());
		
	}

}
