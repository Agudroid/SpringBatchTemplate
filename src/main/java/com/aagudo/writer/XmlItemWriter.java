package com.aagudo.writer;

import java.io.File;

import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;

@Component
public class XmlItemWriter {

	public StaxEventItemWriter<StudentDTO> xmlItemWriter(){
		
		StaxEventItemWriter<StudentDTO> xmlItemWriter =
				new StaxEventItemWriter<>();
		
		xmlItemWriter.setResource(new FileSystemResource(new File("C:\\Users\\Antonio\\git\\SpringBatchTaskletTemplate\\spring-batch\\outputFiles\\students.xml")));
		xmlItemWriter.setRootTagName("students");
		xmlItemWriter.setMarshaller(new Jaxb2Marshaller() {
			{
				setClassesToBeBound(StudentDTO.class);
			}
		});
		
		return xmlItemWriter;
	}
}
