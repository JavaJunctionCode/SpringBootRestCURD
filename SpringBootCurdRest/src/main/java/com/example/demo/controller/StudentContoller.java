package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentContoller {

	@Autowired
	private StudentService service;

	@PostMapping("/save")
	public ResponseEntity<String> createStudent(@RequestBody Student s)

	{
		Integer save = service.save(s);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("Student Created with id: " + save,
				HttpStatus.CREATED);
		return responseEntity;

	}

	@PostMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody Student s) {
		ResponseEntity<String> response = null;
		try {
			service.update(s);
			response = ResponseEntity.ok("STUDENT '" + s.getId() + "' UPDATED");
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Student> getOneStudent(@PathVariable ("id") Integer id) {

		ResponseEntity<Student> response = null;
		try {
			Student oneStudent = service.getOneStudent(id);
			response = ResponseEntity.ok(oneStudent);
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;

	}

	@GetMapping("/remove/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable ("id") Integer id) {

		ResponseEntity<String> response = null;
		try {
			service.delete(id);
			response = ResponseEntity.ok("STUDENT '" + id + "' REMOVED");
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;

	}

	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> list = service.getAllStudent();

		// return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
		return ResponseEntity.ok(list);
	}

}
