package com.microservices.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.student.entity.Student;
import com.microservices.student.repository.StudentRepository;
import com.microservices.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student getStudentById(int id) {
		return studentRepository.findById(id).orElseThrow();
	}

	@Override
	public List<Student> getAllStudentsInfo() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getStudentByDepartmentId(int id) {
		return studentRepository.getStudentByDepartmentId(id);
	}

}
