package com.app.readwritexml.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * This Rest service will invoke batch job parse and read Data from XML file and writing into DB
 *  We can also read data from Cache using  Endpoint : "/readingFromCache" 
 * 
 * 
 */

@RestController
public class RestXMLJobLauncher {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestXMLJobLauncher.class);
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job processJobReadWriteXML;
	

	@RequestMapping("/invokeJobReadWriteXML")
	public String jobInvokerForReadWriteXML() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		LOGGER.info(" INSIDE jobInvokerForReadWriteXML() invoking Job to read and write xml .. ");
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(processJobReadWriteXML, jobParameters);
		return " The batch job for Reading and writing XML file is running..";
	}

}
