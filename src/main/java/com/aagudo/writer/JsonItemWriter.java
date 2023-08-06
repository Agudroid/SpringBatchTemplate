package com.aagudo.writer;

import java.io.File;

import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;

@Component
public class JsonItemWriter {

	public JsonFileItemWriter<StudentDTO> jsonItemWriter() {

		JsonFileItemWriter<StudentDTO> jsonItemWriter = new JsonFileItemWriter<>(new FileSystemResource(new File(
				"C:\\Users\\Antonio\\git\\SpringBatchTaskletTemplate\\spring-batch\\outputFiles\\students.json")),
				new JacksonJsonObjectMarshaller<>());

		return jsonItemWriter;
	}
}
