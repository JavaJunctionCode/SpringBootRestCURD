package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepo repo;

	@Override
	public Integer save(Student s) {
		Student save = repo.save(s);
		return save.getId();
	}

	@Override
	public void update(Student s) {
		if (s.getId() == null)
			throw new StudentNotFoundException("STUDENT NOT EXIST");
		else
			repo.save(s);

	}

	@Override
	public void delete(Integer id) {
		repo.delete(getOneStudent(id));
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> list = repo.findAll();
		return list;
	}

	@Override
	public Student getOneStudent(Integer id) {
		return repo.findById(id).orElseThrow(() -> new StudentNotFoundException("STUDENT '" + id + "' NOT EXIST"));
	}
}
