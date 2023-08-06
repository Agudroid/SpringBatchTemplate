package com.aagudo.writer;

import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;
import com.aagudo.service.StudentService;

@Component
public class ApiItemWriter {

	@Autowired
	StudentService studentService;

	public ItemWriterAdapter<StudentDTO> itemWriterAdapter(){
		
		ItemWriterAdapter<StudentDTO> itemWriterAdapter = 
				new ItemWriterAdapter<>();
		
		itemWriterAdapter.setTargetObject(studentService);
		itemWriterAdapter.setTargetMethod("restCallToCreateStudent");
		return itemWriterAdapter;
		
	}
}
