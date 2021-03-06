package com.app.readwritexml.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;


public class JobCompletionListener extends JobExecutionListenerSupport{

	private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionListener.class);
	@Override
	public void afterJob(JobExecution jobExecution) {
		
		if (jobExecution.getStatus()==BatchStatus.COMPLETED) {
			LOGGER.info("BATCH JOB to read and write XML is completed successfully");
		}
	}

	
}
