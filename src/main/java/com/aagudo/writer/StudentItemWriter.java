package com.aagudo.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;

@Component
public class StudentItemWriter implements ItemWriter<StudentDTO>{

	@Override
	public void write(List<? extends StudentDTO> items) throws Exception {
		System.out.println("Inside item Writer");
		items.stream().forEach(System.out::println);
		
	}

}
