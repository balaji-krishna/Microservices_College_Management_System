package com.microservices.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.student.entity.Student;
import com.microservices.student.httpData.StudentResponseBody;
import com.microservices.student.service.StudentService;

@RestController
@RequestMapping(path = "student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping(path = "getAllStudentsInfo")
	public StudentResponseBody getAllStudentsInfo() throws Exception {
		return studentService.getAllStudentsInfo();
	}
	
	@GetMapping(path = "getStudentById/{id}")
	public StudentResponseBody getStudentById(@PathVariable int id) throws Exception {
		return studentService.getStudentById(id);
	}
	
	@PostMapping(path = "saveStudent")
	public StudentResponseBody saveStudent(@RequestBody Student student) throws Exception {
		return studentService.saveStudent(student);
	}
	
	@GetMapping(path = "getStudentByDepartmentId/{id}")
	public StudentResponseBody getStudentByDepartmentId(@PathVariable int id) throws Exception {
		return studentService.getStudentByDepartmentId(id);
	}
	
	@PutMapping(path = "updateStudent")
	public StudentResponseBody updateStudent(@RequestBody Student student) throws Exception {
		return studentService.updateStudent(student);
	}
	
	@DeleteMapping(path = "deleteStudentById/{id}")
	public StudentResponseBody deleteStudentById(@PathVariable int id) throws Exception {
		return studentService.deleteStudentById(id);
	}

}
