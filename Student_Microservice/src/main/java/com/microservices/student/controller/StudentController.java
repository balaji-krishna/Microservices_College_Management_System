package com.microservices.student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping(path = "getAllStudentsInfo")
	public StudentResponseBody getAllStudentsInfo() throws Exception {
		LOGGER.info("The api /getAllStudentsInfo is called");
		return studentService.getAllStudentsInfo();
	}
	
	@GetMapping(path = "getStudentById/{id}")
	public StudentResponseBody getStudentById(@PathVariable int id) throws Exception {
		LOGGER.info("The api /getStudentById/{} is called", id);
		return studentService.getStudentById(id);
	}
	
	@PostMapping(path = "saveStudent")
	public StudentResponseBody saveStudent(@RequestBody Student student) throws Exception {
		LOGGER.info("The api /saveStudent is called");
		return studentService.saveStudent(student);
	}
	
	@GetMapping(path = "getStudentByDepartmentId/{id}")
	public StudentResponseBody getStudentByDepartmentId(@PathVariable int id) throws Exception {
		LOGGER.info("The api /getStudentByDepartmentId/{} is called", id);
		return studentService.getStudentByDepartmentId(id);
	}
	
	@PutMapping(path = "updateStudent")
	public StudentResponseBody updateStudent(@RequestBody Student student) throws Exception {
		LOGGER.info("The api /updateStudent is called");
		return studentService.updateStudent(student);
	}
	
	@DeleteMapping(path = "deleteStudentById/{id}")
	public StudentResponseBody deleteStudentById(@PathVariable int id) throws Exception {
		LOGGER.info("The api /deleteStudentById/{} is called", id);
		return studentService.deleteStudentById(id);
	}

}
