package com.showjsonapp.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.showjsonapp.entity.PaymentIdentification;

/*
 * 
 * This Controller will hit ReadWriteCacheService which is running on 8082 and fetch the data and show in JSON format
 * Endpoint URL : "http://localhost:8082/readingFromCache" rest
 */

@RestController
public class DisplayDataController {

	private static final String CACHE_SERVICE_URL = "http://localhost:8082/readingFromCache";
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(path = "/displayJson", produces = "application/json")
	public PaymentIdentification displayJson() {
		
		 PaymentIdentification pid = restTemplate.getForEntity(CACHE_SERVICE_URL, PaymentIdentification.class).getBody();
		 
		return pid;
	}
}
