package com.aagudo.listener;


import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener{

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("stepName: " + stepExecution.getStepName());
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("finishing step: " + stepExecution.getStepName());
		return null;
	}



}
