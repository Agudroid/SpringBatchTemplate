package com.aagudo.reader;

import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;
import com.aagudo.service.StudentService;

@Component
public class ApiItemReader {
	
	@Autowired
	StudentService studentService;

	public ItemReaderAdapter<StudentDTO> itemReaderAdapter(){
		
		ItemReaderAdapter<StudentDTO> itemReaderAdapter = 
				new ItemReaderAdapter<>();
		
		itemReaderAdapter.setTargetObject(studentService);
		itemReaderAdapter.setTargetMethod("getStudent");
		return itemReaderAdapter;
		
	}
	
}
