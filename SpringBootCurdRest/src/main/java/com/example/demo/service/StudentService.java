package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Student;

public interface StudentService {
	
	Integer save(Student s);
	void update(Student s);
	void delete(Integer id);
	List<Student> getAllStudent();
	Student getOneStudent(Integer id);


}
