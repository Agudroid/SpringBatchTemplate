package com.aagudo.reader;

import java.io.File;

import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;

@Component
public class JsonExampleItemReader {

	public JsonItemReader<StudentDTO> jsonItemReader(){
		
		JsonItemReader<StudentDTO> jsonItemReader = 
				new JsonItemReader<StudentDTO>();
		
		jsonItemReader.setResource(new FileSystemResource(new File("C:\\Users\\Antonio\\git\\SpringBatchTaskletTemplate\\spring-batch\\inputFiles\\students.json")));
		jsonItemReader.setJsonObjectReader(
				new JacksonJsonObjectReader<>(StudentDTO.class));
		
		return jsonItemReader;
	}
}
