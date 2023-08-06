package com.aagudo.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aagudo.model.StudentDTO;

@Component
public class JdbcItemWriter {

	@Autowired
	private DataSource dataSource;
	
	public JdbcBatchItemWriter<StudentDTO> jdbcItemWriter(){
		
		JdbcBatchItemWriter<StudentDTO> jdbcBatchItemWriter =
				new JdbcBatchItemWriter<>();
		
		jdbcBatchItemWriter.setDataSource(dataSource);
		jdbcBatchItemWriter.setSql(
				"insert into student(id, firstname, lastname, email)"
				+ "values (?,?,?,?)");
		
		jdbcBatchItemWriter.setItemPreparedStatementSetter(
				new ItemPreparedStatementSetter<StudentDTO>() {
					
					@Override
					public void setValues(StudentDTO item, PreparedStatement ps) throws SQLException {
						ps.setLong(1, item.getId());
						ps.setString(2, item.getFirstName());
						ps.setString(3, item.getLastName());
						ps.setString(4, item.getEmail());
						
					}
				});
		
		
		return jdbcBatchItemWriter;
	}
}
