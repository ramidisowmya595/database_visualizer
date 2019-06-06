package com.example.demo.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.model.Data;
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
	
	@Bean
	public DatabaseMetaDataReader reader() throws SQLException{
		
		System.out.println("reader invoked");
		DatabaseMetaDataReader CursorItemReader = new DatabaseMetaDataReader(dataSource);
		//CursorItemReader.setDataSource(dataSource);
		//CursorItemReader.setSql("show database;");
		//CursorItemReader.setRowMapper(new DataRowMapper());
		System.out.println("CursorItemReader="+CursorItemReader);
		return CursorItemReader;
	}
	 @Bean
	    public JdbcBatchItemWriter<Data> writer() {
		 System.out.println("writter invoked");
		 JdbcBatchItemWriter<Data> builder = new JdbcBatchItemWriterBuilder<Data>()
	            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	        .sql("INSERT INTO visualdb.insertdb(type_nm,schema_nm,table_nm,column_nm) VALUES(:type_nm,:schema_nm,:table_nm,:column_nm)")
	            .dataSource(dataSource)
	            .build();
			 return builder;

	    }
	
	@Bean
	public DataItemProcessor processor() {
		return new DataItemProcessor();
	}
	
	/*	@Bean
	public FlatFileItemWriter<Data> writer(){
		FlatFileItemWriter<Data> writer = new FlatFileItemWriter<Data>();
		writer.setResource(new ClassPathResource("datas.csv"));
		
		DelimitedLineAggregator<Data> lineAggregator = new DelimitedLineAggregator<Data>();
		lineAggregator.setDelimiter(",");
		
		BeanWrapperFieldExtractor<Data>  fieldExtractor = new BeanWrapperFieldExtractor<Data>();
		fieldExtractor.setNames(new String[]{"id","type_nm","schema_nm","table_nm","column_nm"});
		lineAggregator.setFieldExtractor(fieldExtractor);
		
		writer.setLineAggregator(lineAggregator);
		return writer;
	}*/
	
	@Bean
	public Step step1() throws SQLException {
		return stepbuilderfactory.get("step1").<Data,Data>chunk(1).reader(reader()).processor(processor()).writer(writer()).build();
	}
	
	@Bean
	public Job exportdataJob() throws SQLException {
		return jobbuilderfactory.get("exportdataJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
}
