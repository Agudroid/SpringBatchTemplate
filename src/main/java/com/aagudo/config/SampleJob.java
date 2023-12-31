package com.aagudo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aagudo.listener.FirstJobListener;
import com.aagudo.listener.FirstStepListener;
import com.aagudo.listener.SkipListener;
import com.aagudo.model.StudentDTO;
import com.aagudo.processor.FirstItemProcessor;
import com.aagudo.reader.ApiItemReader;
import com.aagudo.reader.CsvItemReader;
import com.aagudo.reader.DummyItemReader;
import com.aagudo.reader.JdbcItemReader;
import com.aagudo.reader.JsonExampleItemReader;
import com.aagudo.reader.XmlItemReader;
import com.aagudo.service.FirstTasklet;
import com.aagudo.service.SecondTasklet;
import com.aagudo.writer.ApiItemWriter;
import com.aagudo.writer.CsvItemWriter;
import com.aagudo.writer.DummyItemWriter;
import com.aagudo.writer.JdbcItemWriter;
import com.aagudo.writer.JsonItemWriter;
import com.aagudo.writer.StudentItemWriter;
import com.aagudo.writer.XmlItemWriter;


@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private FirstTasklet firstTasklet;
	
	@Autowired
	private SecondTasklet secondTasklet;
	
	@Autowired
	private FirstJobListener firstJobListener;
	
	@Autowired
	private FirstStepListener stepExecutionListener;
	
	@Autowired
	private SkipListener skipListener;
	
	/*****************************
	 ***********Readers***********
	 *****************************/
	
	@Autowired
	private DummyItemReader dummyItemReader;
	
	@Autowired
	private CsvItemReader csvItemReader;
	
	@Autowired
	private JsonExampleItemReader jsonItemReader;
	
	@Autowired
	private XmlItemReader xmlItemReader;
	
	@Autowired
	private JdbcItemReader jdbcItemReader;
	
	@Autowired
	private ApiItemReader apiItemReader;
	
	/*****************************
	 ***********Processors***********
	 *****************************/
	
	@Autowired
	private FirstItemProcessor firstItemProcessor;
	
	
	/*****************************
	 ***********Writers***********
	 *****************************/
	
	@Autowired
	private StudentItemWriter studentItemWriter;
	
	@Autowired
	private DummyItemWriter dummyItemWriter;
	
	@Autowired
	private CsvItemWriter csvItemWriter;
	
	@Autowired
	private JsonItemWriter jsonItemWriter;
	
	@Autowired
	private XmlItemWriter xmlItemWriter;
	
	@Autowired
	private JdbcItemWriter jdbcItemWriter;
	
	@Autowired
	private ApiItemWriter apiItemWriter;
	
	@Bean
	public Job taskletJob() {
		return this.jobBuilderFactory.get("First Job")
			.incrementer(new RunIdIncrementer())
			.listener(this.firstJobListener)
			.start(firtTaskletStep())
			.next(secondTaskletStep())
			.build();
			
	}
	
	private Step firtTaskletStep() {
		return this.stepBuilderFactory.get("First Step")
			.tasklet(this.firstTasklet)
			.build();
		
	}
	
	private Step secondTaskletStep() {
		return this.stepBuilderFactory.get("Second Step")
				.listener(this.stepExecutionListener)
				.tasklet(this.secondTasklet)
				.build();
	}
	
	@Bean
	public Job chunkJob() {
		
		return jobBuilderFactory.get("Chunk Job")
				.incrementer(new RunIdIncrementer())
				.start(dummyChunkStep())
				.next(csvChunkStep())
				.next(jsonChunkStep())
				.next(xmlChunkStep())
				.next(jdbcChunkStep())
				.next(apiChunkStep())
				.build();
	}
	
	private Step dummyChunkStep() {
		return this.stepBuilderFactory.get("dummyChunkStep")
				.<Integer, Long>chunk(3)
				.reader(this.dummyItemReader)
				.processor(this.firstItemProcessor)
				.writer(this.dummyItemWriter)
				.build();
	
	}
	
	private Step csvChunkStep() {
		return this.stepBuilderFactory.get("csvChunkStep")
				.<StudentDTO, StudentDTO>chunk(3)
				.reader(this.csvItemReader.flatFileItemReader())
				.writer(this.csvItemWriter.csvItemWriter())
				.build();
	}
	
	private Step jsonChunkStep() {
		return this.stepBuilderFactory.get("JSONChunkStep")
				.<StudentDTO, StudentDTO>chunk(3)
				.reader(this.jsonItemReader.jsonItemReader())
				.writer(this.jsonItemWriter.jsonItemWriter())
				.build();
	}
	
	private Step xmlChunkStep() {
		return this.stepBuilderFactory.get("XMLChunkStep")
				.<StudentDTO, StudentDTO>chunk(3)
				.reader(this.xmlItemReader.staxEventItemReader())
				.writer(this.xmlItemWriter.xmlItemWriter())
				.build();
	}

	private Step jdbcChunkStep() {
		return this.stepBuilderFactory.get("DatabaseChunkStep")
				.<StudentDTO, StudentDTO>chunk(3)
				.reader(this.csvItemReader.flatFileItemReader())
				.writer(this.jdbcItemWriter.jdbcItemWriter())
				.build();
	}
	
	private Step apiChunkStep() {
		return this.stepBuilderFactory.get("RESTAPIChunkStep")
				.<StudentDTO, StudentDTO>chunk(3)
				.reader(this.apiItemReader.itemReaderAdapter())
				.writer(this.apiItemWriter.itemWriterAdapter())
				.build();
	}
	
	
	@Bean
	public Job faultToleranceJob() {
		
		return jobBuilderFactory.get("toleranceJob")
				.incrementer(new RunIdIncrementer())
				.start(toleranceStep())
				.build();
	}
	
	private Step toleranceStep() {
		return this.stepBuilderFactory.get("FaultToleranceStep")
				.<StudentDTO, StudentDTO>chunk(3)
				.reader(this.csvItemReader.flatFileItemReader())
				.writer(this.jsonItemWriter.jsonItemWriter())
				.faultTolerant()
				.skip(FlatFileParseException.class)
				.skip(NullPointerException.class)
				.skip(Throwable.class)
				//.skipLimit(10)
				.skipPolicy(new AlwaysSkipItemSkipPolicy())
				.listener(skipListener)
				.build();
	}

}
