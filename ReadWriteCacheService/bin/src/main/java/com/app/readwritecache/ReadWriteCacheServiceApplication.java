package com.app.readwritecache;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableBatchProcessing
@EnableCaching
@ComponentScan({"com.app"})
@EntityScan(basePackages = {"com.app.readwritecache.entity"})
public class ReadWriteCacheServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadWriteCacheServiceApplication.class, args);
	}

}
