package com.aagudo.reader;

import java.io.File;

import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;

@Component
public class XmlItemReader {

	public StaxEventItemReader<StudentDTO> staxEventItemReader(){
		StaxEventItemReader<StudentDTO> staxEventItemReader =
				new StaxEventItemReader<>();
		
		staxEventItemReader.setResource(new FileSystemResource(new File("C:\\Users\\Antonio\\git\\SpringBatchTaskletTemplate\\spring-batch\\inputFiles\\students.xml")));
		staxEventItemReader.setFragmentRootElementName("student");
		staxEventItemReader.setUnmarshaller(new Jaxb2Marshaller() {
			{
				setClassesToBeBound(StudentDTO.class);
			}
		});
		
		return staxEventItemReader;
	}
}
