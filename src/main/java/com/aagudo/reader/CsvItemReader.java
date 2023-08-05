package com.aagudo.reader;

import java.io.File;


import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;

@Component
public class CsvItemReader {

	public FlatFileItemReader<StudentDTO> flatFileItemReader(){
		FlatFileItemReader<StudentDTO> flatFileItemReader = new FlatFileItemReader<StudentDTO>();
		flatFileItemReader.setResource(new FileSystemResource(new File("C:\\Users\\Antonio\\git\\SpringBatchTaskletTemplate\\spring-batch\\inputFiles\\students.csv")));
		flatFileItemReader.setLineMapper(new DefaultLineMapper<StudentDTO>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames("ID","First Name","Last Name","Email");
					
					}
				});
				
				setFieldSetMapper(new BeanWrapperFieldSetMapper<StudentDTO>() {
					{
						setTargetType(StudentDTO.class);
					}
				});
			}
		});
		
		flatFileItemReader.setLinesToSkip(1);
		
		return flatFileItemReader;
	}

}
