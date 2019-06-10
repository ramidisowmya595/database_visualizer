package com.example.demo.config;

import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.demo.model.Data;
import com.example.demo.model.People;
import com.example.demo.processor.Appitemprocess;
import com.example.demo.processor.DataItemProcessor;



@Configuration
@EnableBatchProcessing
public class Config {
	 
	@Autowired
	private JobBuilderFactory jobbuilderfactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderfactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private MongoTemplate templete;
	
	@Bean
	public DatabaseMetaDataReader reader() throws SQLException{
		
		System.out.println("reader invoked");
		DatabaseMetaDataReader CursorItemReader = new DatabaseMetaDataReader(dataSource);
		//CursorItemReader.setDataSource(dataSource);
		//CursorItemReader.setSql("show database;");
		//CursorItemReader.setRowMapper(new DataRowMapper());
		//System.out.println("CursorItemReader="+CursorItemReader);
		return CursorItemReader;
	}
	 @Bean
	    public JdbcBatchItemWriter<Data> writer() {
		 System.out.println("writter invoked");
		 JdbcBatchItemWriter<Data> builder = new JdbcBatchItemWriterBuilder<Data>()
	            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	        .sql("INSERT INTO visualdb.targetdb(type_nm,schema_nm,table_nm,column_nm) VALUES(:type_nm,:schema_nm,:table_nm,:column_nm)")
	            .dataSource(dataSource)
	            .build();
			 return builder;
			 
	    }
	 
	@Bean
	public DataItemProcessor processor() {
		return new DataItemProcessor();
	}
	
	
	@Bean
	public Step step1() throws SQLException {
		return stepbuilderfactory.get("step1").<Data,Data>chunk(1).reader(reader()).processor(processor()).writer(writer()).build();
	}
	
	@Bean
	public Job exportdataJob() throws SQLException {
		return jobbuilderfactory.get("exportdataJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
	
	
	
	
	@Bean
	public MongoItemReader<People> reader1(){
		MongoItemReader<People> reader = new MongoItemReader<People>();
		reader.setTemplate(templete);
		reader.setQuery("{}");
		reader.setTargetType(People.class);
		reader.setSort(new HashMap<String, Sort.Direction>(){{
			put("username",Direction.DESC);
		}
		});
		//System.err.println("redr"+reader);
		return reader;
	}
	
	
	
	@Bean
	public Job exportdataJob1() throws SQLException {
		return jobbuilderfactory.get("exportdataJob1").incrementer(new RunIdIncrementer()).flow(step2()).end().build();
	}
	
	@Bean
	public Step step2() throws SQLException {
		return stepbuilderfactory.get("step2").<People,Data>chunk(1).reader(reader1()).processor(processor1()).writer(writer()).build();
	}
	@Bean
	public Appitemprocess processor1() {
		return new Appitemprocess();
	}
}
