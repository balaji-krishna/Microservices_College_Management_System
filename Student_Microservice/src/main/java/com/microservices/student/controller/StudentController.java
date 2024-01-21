package com.microservices.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.student.entity.Student;
import com.microservices.student.service.StudentService;

@RestController
@RequestMapping(path = "student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping(path = "getAllStudentsInfo")
	public List<Student> getAllStudentsInfo() {
		return studentService.getAllStudentsInfo();
	}
	
	@GetMapping(path = "getStudentById/{id}")
	public Student getStudentById(@PathVariable int id) {
		return studentService.getStudentById(id);
	}
	
	@PostMapping(path = "saveStudent")
	public Student saveStudent(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}
	
	@GetMapping(path = "getStudentByDepartmentId/{id}")
	public List<Student> getStudentByDepartmentId(@PathVariable int id) {
		return studentService.getStudentByDepartmentId(id);
	}

}
