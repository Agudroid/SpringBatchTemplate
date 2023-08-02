package com.aagudo.controller;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aagudo.service.JobService;

@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	JobOperator jobOperator;
	
	@Autowired
	JobService jobService;
	
	@GetMapping("/start/{jobName}")
	public String startJob(@PathVariable String jobName) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		this.jobService.startJob(jobName);
		return "Job Started...";
	}
	
	@GetMapping("/stop/{executionId}")
	public String stopJob(@PathVariable long executionId) {
		try {
			this.jobOperator.stop(executionId);
		} catch (NoSuchJobExecutionException e) {
			System.out.println("Job not found");
		} catch (JobExecutionNotRunningException e) {
			System.out.println("Job not running");

		}
		return "Job Stopped..";

	}
}
