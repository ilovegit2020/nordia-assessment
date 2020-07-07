package com.app.readwritecache.batch.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.app.readwritecache.batch.item.WriteToStoreDataInCache;
import com.app.readwritecache.entity.PaymentIdentification;

@Configuration
public class BatchConfig {

	private static final String SQL_QUERY = "SELECT instrId,endToEndId FROM paymentidentification";

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
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

	@Bean
	 public JdbcCursorItemReader<PaymentIdentification> reader(){
	  JdbcCursorItemReader<PaymentIdentification> reader = new JdbcCursorItemReader<PaymentIdentification>();
	  reader.setDataSource(dataSource);
	  reader.setSql(SQL_QUERY);
	  reader.setRowMapper(new CustomRowMapper());
	  
	  return reader;
	 }
	 public class CustomRowMapper implements RowMapper<PaymentIdentification>{

		  @Override
		  public PaymentIdentification mapRow(ResultSet rs, int rowNum) throws SQLException {
			  PaymentIdentification pid = new PaymentIdentification();
			  pid.setInstrId(rs.getString("instrId"));
			  pid.setEndToEndId(rs.getString("endToEndId"));
		   return pid;
		  }
	
	@Bean
	public Step orderStepReadWriteCache() {
		return stepBuilderFactory.get("orderStepReadWriteCache").<PaymentIdentification, PaymentIdentification>chunk(1)
				.reader(reader()).writer(new WriteToStoreDataInCache()).build();

	}

	@Bean
	public Job processJobReadWriteCache() {
		// return jobBuilderFactory.get("processJobReadWriteCache").incrementer(new
		// RunIdIncrementer()).listener(listener())
		// .flow(orderStepReadWriteCache()).end().build();
		return jobBuilderFactory.get("processJobReadWriteCache").listener(listener()).flow(orderStepReadWriteCache())
				.end().build();
	}

}
}