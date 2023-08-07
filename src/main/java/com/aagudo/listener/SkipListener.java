package com.aagudo.listener;

import java.io.FileWriter;
import java.util.Date;

import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

@Component
public class SkipListener {

	@OnSkipInRead
	public void skipInRead(Throwable th) {
		if(th instanceof FlatFileParseException) {
			createFile("C:\\Users\\Antonio\\git\\SpringBatchTaskletTemplate\\spring-batch\\outputFiles\\errorFiles\\readerError.txt", 
					((FlatFileParseException) th).getInput());
		}
	}
	
	public void createFile(String filePath, String data) {
		try (FileWriter fileWriter = new FileWriter(filePath)){
			fileWriter.write(data + " " + new Date() + "\n");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
