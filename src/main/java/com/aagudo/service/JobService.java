package com.aagudo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class JobService {

	@Autowired
	JobLauncher jobLauncher;
	
	@Qualifier("taskletJob")
	@Autowired
	Job firstJob;
	
	
	@Qualifier("chunkJob")
	@Autowired
	Job secondJob;
	
	@Async
	public void startJob(String jobName) {
		Map<String, JobParameter> params = new HashMap<>();
		params.put("currentTime", new JobParameter(System.currentTimeMillis()));
		
		JobParameters jobParameters = new JobParameters(params);
		try {
			if(jobName.equals("First Job")) {
				this.jobLauncher.run(firstJob, jobParameters);
			} else {
				this.jobLauncher.run(secondJob, jobParameters);
			}
		}
		catch(Exception e) {
			System.out.println("Exception while starting the job..");
		}
		
		
	}
}
