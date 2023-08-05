package com.aagudo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aagudo.model.StudentDTO;


@Service
public class StudentService {

	ArrayList<StudentDTO> studentList;
	
	public List<StudentDTO> restCallToGetStudents(){
		RestTemplate restTemplate = new RestTemplate();
		StudentDTO[] studentResponseArray = restTemplate.getForObject("http://localhost:8081/api/v1/students"
				, StudentDTO[].class);
		studentList = new ArrayList<>();
		for(StudentDTO student: studentResponseArray) {
			studentList.add(student);
		}
		return studentList;
	}
	
	public StudentDTO getStudent() {
		
		if(studentList == null) {
			restCallToGetStudents();
		}
		
		if(studentList != null && !studentList.isEmpty()) {
			return studentList.remove(0);
		}
		
		return null;
	}
}
