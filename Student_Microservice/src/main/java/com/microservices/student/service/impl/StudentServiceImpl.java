package com.microservices.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.student.constant.ApplicationConstant;
import com.microservices.student.entity.Student;
import com.microservices.student.exception.BusinessException;
import com.microservices.student.httpData.StudentResponseBody;
import com.microservices.student.repository.StudentRepository;
import com.microservices.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Cacheable(cacheNames = "studentById_cache", key = "#id")
	public StudentResponseBody getStudentById(int id) throws Exception {
		LOGGER.info("The method getStudentById is called with id {}", id);
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		if (!studentRepository.existsById(id)) {
			throw new BusinessException("Student Id does not exists!!");
		}
		studentResponseBody.setStudent(studentRepository.findById(id).get());
		studentResponseBody.setResultCode(ApplicationConstant.SUCCESS_RESULT_CODE);
		studentResponseBody.setResultMessage(ApplicationConstant.SUCCESSFULLY_COMPLETED);
		LOGGER.info("The method getStudentById is successfully completed");
		return studentResponseBody;
	}

	@Override
	public StudentResponseBody getAllStudentsInfo() {
		LOGGER.info("The method getAllStudentsInfo is called");
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setStudents(studentRepository.findAll());
		studentResponseBody.setResultCode(ApplicationConstant.SUCCESS_RESULT_CODE);
		studentResponseBody.setResultMessage(ApplicationConstant.SUCCESSFULLY_COMPLETED);
		LOGGER.info("The method getAllStudentsInfo is successfully completed");
		return studentResponseBody;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public StudentResponseBody saveStudent(Student student) throws Exception {
		LOGGER.info("The method saveStudent is called with id {}", student.getId());
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		if (studentRepository.existsById(student.getId())) {
			throw new BusinessException("Student Id already exists!!");
		}
		studentResponseBody.setStudent(studentRepository.save(student));
		studentResponseBody.setResultCode(ApplicationConstant.SUCCESS_RESULT_CODE);
		studentResponseBody.setResultMessage(ApplicationConstant.SUCCESSFULLY_COMPLETED);
		LOGGER.info("The method saveStudent is successfully completed");
		return studentResponseBody;
	}

	@Override
	public StudentResponseBody getStudentByDepartmentId(int id) throws Exception {
		LOGGER.info("The method getStudentByDepartmentId is called with id {}", id);
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		List<Integer> departmentIds = new ArrayList<>();
		List<Student> students = studentRepository.findAll();
		for (Student student : students) {
			if (!departmentIds.contains(student.getDepartmentId()))
				departmentIds.add(student.getDepartmentId());
		}
		if (!departmentIds.contains(id)) {
			throw new BusinessException("Department Id does not exists!!");
		}
		studentResponseBody.setStudents(studentRepository.getStudentByDepartmentId(id));
		studentResponseBody.setResultCode(ApplicationConstant.SUCCESS_RESULT_CODE);
		studentResponseBody.setResultMessage(ApplicationConstant.SUCCESSFULLY_COMPLETED);
		LOGGER.info("The method getStudentByDepartmentId is successfully completed");
		return studentResponseBody;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@CachePut(cacheNames = "studentById_cache", key = "#student.id")
	public StudentResponseBody updateStudent(Student student) throws Exception {
		LOGGER.info("The method updateStudent is called with id {}", student.getId());
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		if (!studentRepository.existsById(student.getId())) {
			throw new BusinessException("Student Id does not exists!!");
		}
		studentResponseBody.setStudent(studentRepository.save(student));
		studentResponseBody.setResultCode(ApplicationConstant.SUCCESS_RESULT_CODE);
		studentResponseBody.setResultMessage(ApplicationConstant.SUCCESSFULLY_COMPLETED);
		LOGGER.info("The method updateStudent is successfully completed");
		return studentResponseBody;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	@CacheEvict(cacheNames = "studentById_cache", key = "#id")
	public StudentResponseBody deleteStudentById(int id) throws Exception {
		LOGGER.info("The method deleteStudentById is called with id {}", id);
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		if (!studentRepository.existsById(id)) {
			throw new BusinessException("Student Id does not exists!!");
		}
		studentRepository.deleteById(id);
		studentResponseBody.setStudents(studentRepository.findAll());
		studentResponseBody.setResultCode(ApplicationConstant.SUCCESS_RESULT_CODE);
		studentResponseBody.setResultMessage(ApplicationConstant.SUCCESSFULLY_COMPLETED);
		LOGGER.info("The method deleteStudentById is successfully completed");
		return studentResponseBody;
	}

}
