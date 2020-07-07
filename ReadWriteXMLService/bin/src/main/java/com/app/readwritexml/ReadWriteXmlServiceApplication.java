package com.app.readwritexml;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.app"})
@EntityScan(basePackages = {"com.app.readwritexml.entity"})
public class ReadWriteXmlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadWriteXmlServiceApplication.class, args);
	}

}
