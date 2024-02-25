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
import org.springframework.web.bind.annotation.RequestHeader;
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
	public StudentResponseBody getAllStudentsInfo(@RequestHeader(value = "X-Correlation-Id") String correlationId)
			throws Exception {
		LOGGER.info("The api /getAllStudentsInfo is called with X-Correlation-Id: {}", correlationId);
		return studentService.getAllStudentsInfo();
	}

	@GetMapping(path = "getStudentById/{id}")
	public StudentResponseBody getStudentById(@PathVariable int id,
			@RequestHeader(value = "X-Correlation-Id") String correlationId) throws Exception {
		LOGGER.info("The api /getStudentById/{} is called with X-Correlation-Id: {}", id, correlationId);
		return studentService.getStudentById(id);
	}

	@PostMapping(path = "saveStudent")
	public StudentResponseBody saveStudent(@RequestBody Student student,
			@RequestHeader(value = "X-Correlation-Id") String correlationId) throws Exception {
		LOGGER.info("The api /saveStudent is called with X-Correlation-Id: {}", correlationId);
		return studentService.saveStudent(student);
	}

	@GetMapping(path = "getStudentByDepartmentId/{id}")
	public StudentResponseBody getStudentByDepartmentId(@PathVariable int id,
			@RequestHeader(value = "X-Correlation-Id") String correlationId) throws Exception {
		LOGGER.info("The api /getStudentByDepartmentId/{} is called with X-Correlation-Id: {}", id, correlationId);
		return studentService.getStudentByDepartmentId(id);
	}

	@PutMapping(path = "updateStudent")
	public StudentResponseBody updateStudent(@RequestBody Student student,
			@RequestHeader(value = "X-Correlation-Id") String correlationId) throws Exception {
		LOGGER.info("The api /updateStudent is called with X-Correlation-Id: {}", correlationId);
		return studentService.updateStudent(student);
	}

	@DeleteMapping(path = "deleteStudentById/{id}")
	public StudentResponseBody deleteStudentById(@PathVariable int id,
			@RequestHeader(value = "X-Correlation-Id") String correlationId) throws Exception {
		LOGGER.info("The api /deleteStudentById/{} is called with X-Correlation-Id: {}", id, correlationId);
		return studentService.deleteStudentById(id);
	}
	
	@GetMapping(path = "aroundAdviceDemo")
	public String aroundAdviceDemo(@RequestHeader(value = "X-Correlation-Id") String correlationId)
			throws Exception {
		LOGGER.info("The api /aroundAdviceDemo is called with X-Correlation-Id: {}", correlationId);
		return studentService.aroundAdviceDemo();
	}
	
	@GetMapping(path = "afterReturnThrowDemo")
	public String afterReturnThrowDemo(@RequestHeader(value = "X-Correlation-Id") String correlationId)
			throws Exception {
		LOGGER.info("The api /afterReturnThrowDemo is called with X-Correlation-Id: {}", correlationId);
		return studentService.afterReturnThrowDemo();
	}

}
