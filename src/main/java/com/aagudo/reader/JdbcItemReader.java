package com.aagudo.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;

@Component
public class JdbcItemReader {

	@Autowired
	private DataSource dataSource;
	
	public JdbcCursorItemReader<StudentDTO> jdbcCursorItemReader(){
		JdbcCursorItemReader<StudentDTO> jdbcCursorItemReader =
				new JdbcCursorItemReader<>();
		
		jdbcCursorItemReader.setDataSource(dataSource);
		jdbcCursorItemReader.setSql("SELECT id, firstname as firstName, lastname as lastName"
				+ "email from springbatch_db.student");
		
		jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<>() {
			{
				setMappedClass(StudentDTO.class);
			}
		});
		
		
		return jdbcCursorItemReader;
	}
}
