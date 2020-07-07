package com.app.readwritexml.batch.config;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String>{

	@Override
	public String process(String item) throws Exception {
		// TODO Auto-generated method stub
		return item.toUpperCase();
	}

}
		