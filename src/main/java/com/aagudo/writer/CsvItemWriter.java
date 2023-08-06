package com.aagudo.writer;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;

@Component
public class CsvItemWriter {

	public FlatFileItemWriter<StudentDTO> csvItemWriter(){
		
		System.out.println("Inside csv itemWriter");
		FlatFileItemWriter<StudentDTO> flatFileItemWriter =
				new FlatFileItemWriter<>();
		
		flatFileItemWriter.setResource(new FileSystemResource(new File("C:\\Users\\Antonio\\git\\SpringBatchTaskletTemplate\\spring-batch\\outputFiles\\students.csv")));
		flatFileItemWriter.setHeaderCallback(new FlatFileHeaderCallback() {
			
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.write("Id|First Name|Last Name|Email");
				
			}
		});
		
		flatFileItemWriter.setLineAggregator(new DelimitedLineAggregator<>() {
			{
				setDelimiter("|");
				setFieldExtractor(new BeanWrapperFieldExtractor<StudentDTO>() {
					{
						setNames(new String[] {
							"id", "firstName","lastName","email"	
						});
					}
				});
			}
		});
		
		flatFileItemWriter.setFooterCallback(new FlatFileFooterCallback() {
			
			@Override
			public void writeFooter(Writer writer) throws IOException {
				writer.write("Created @ " + new Date());
				
			}
		});
		
		return flatFileItemWriter;
	}

}
