package com.aagudo.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class DummyItemWriter implements ItemWriter<Long>{

	@Override
	public void write(List<? extends Long> items) throws Exception {
		System.out.println("Inside item Writer");
		items.stream().forEach(System.out::println);
		
	}


	
}
