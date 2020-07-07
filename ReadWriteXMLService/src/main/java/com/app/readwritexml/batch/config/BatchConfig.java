package com.app.readwritexml.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.app.readwritexml.batch.item.ReaderForXML;
import com.app.readwritexml.entity.PaymentIdentification;


@Configuration
public class BatchConfig {

	private static final String SQL_QUERY = "INSERT INTO payment_identification( instrId, endToEndId) VALUES ( :instrId , :endToEndId )";

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	 @Autowired
	 public DataSource dataSource;
	 
	 @Bean
	 public DataSource dataSource() {
	  final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	  dataSource.setUrl("jdbc:mysql://localhost:3307/batchdb");
	  dataSource.setUsername("root");
	  dataSource.setPassword("root");
	  
	  return dataSource;
	 }
	 
	@Bean
	 public JdbcBatchItemWriter<PaymentIdentification> writer()
	 {
		 JdbcBatchItemWriter<PaymentIdentification> jdbcBatchItemWriter = new JdbcBatchItemWriter<PaymentIdentification>();
		 jdbcBatchItemWriter.setDataSource(dataSource);
		 jdbcBatchItemWriter.setSql(SQL_QUERY);
		 jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<PaymentIdentification>());
		return jdbcBatchItemWriter;
	 }
	 
	@Bean
	public JobExecutionListener listener()
	{
		return new JobCompletionListener();
	}

	@Bean
	public Step orderStepReadWriteXML()
	{
		return stepBuilderFactory.get("orderStepReadWriteXML").<PaymentIdentification, PaymentIdentification>chunk(1)
		.reader(new ReaderForXML()).writer(writer()).build();
		
	}
	
	@Bean
	public Job processJobReadWriteXML()
	{
		return jobBuilderFactory.get("processJobReadWriteXML").incrementer(new RunIdIncrementer()).listener(listener())
		.flow(orderStepReadWriteXML()).end().build();
	}
	
	
}
















