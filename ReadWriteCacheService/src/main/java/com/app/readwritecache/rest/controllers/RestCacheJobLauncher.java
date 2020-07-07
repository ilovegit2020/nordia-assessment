package com.app.readwritecache.rest.controllers;

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
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.readwritecache.batch.item.WriteToStoreDataInCache;
import com.app.readwritecache.cache.CacheStorage;
import com.app.readwritecache.entity.PaymentIdentification;

// Rest URL : http://localhost:8082/invokeJobReadWriteCache

@RestController
@CrossOrigin
public class RestCacheJobLauncher {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestCacheJobLauncher.class);
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job processJobReadWriteCache;

	@Autowired
	CacheStorage cacheStorage;
	
	@Bean
	public WriteToStoreDataInCache getWriteToStoreDataInCache() {
		return new WriteToStoreDataInCache();
	}

	@RequestMapping(path = "/invokeJobReadWriteCache")
	public String jobInvokerForReadWriteCache() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		LOGGER.info("INSIDE jobInvokerForReadWriteCache() -> invoiking Job .. ");
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(processJobReadWriteCache, jobParameters);
		return " WriteToStoreDataInCache service is running";
	}

	@GetMapping("/readingFromCache")
	public PaymentIdentification readingDataFromCache() {
		LOGGER.info("INSIDE readingFromCache() :Reading data from Cache .. ");
		PaymentIdentification output = cacheStorage.getDataFromCache();
		return output;
	}
}
