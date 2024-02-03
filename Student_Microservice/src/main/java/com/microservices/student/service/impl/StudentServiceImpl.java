package com.microservices.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.student.entity.Student;
import com.microservices.student.exception.BusinessException;
import com.microservices.student.httpData.StudentResponseBody;
import com.microservices.student.repository.StudentRepository;
import com.microservices.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public StudentResponseBody getStudentById(int id) throws Exception {
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		if (!studentRepository.existsById(id)) {
			throw new BusinessException("Student Id does not exists!!");
		}
		studentResponseBody.setStudent(studentRepository.findById(id).get());
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");

		return studentResponseBody;
	}

	@Override
	public StudentResponseBody getAllStudentsInfo() throws Exception {
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		try {
			studentResponseBody.setStudents(studentRepository.findAll());
			studentResponseBody.setResultCode("01");
			studentResponseBody.setResultMessage("Successfully completed");
		} catch (Exception e) {
			throw new BusinessException("Exception occurred");
		}

		return studentResponseBody;
	}

	@Override
	public StudentResponseBody saveStudent(Student student) throws Exception {
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		if (studentRepository.existsById(student.getId())) {
			throw new BusinessException("Student Id already exists!!");
		}
		studentResponseBody.setStudent(studentRepository.save(student));
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");

		return studentResponseBody;
	}

	@Override
	public StudentResponseBody getStudentByDepartmentId(int id) throws Exception {
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
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");

		return studentResponseBody;
	}

	@Override
	public StudentResponseBody updateStudent(Student student) throws Exception {
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		if (!studentRepository.existsById(student.getId())) {
			throw new BusinessException("Student Id does not exists!!");
		}
		studentResponseBody.setStudent(studentRepository.save(student));
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");

		return studentResponseBody;
	}

	@Override
	public StudentResponseBody deleteStudentById(int id) throws Exception {
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		if (!studentRepository.existsById(id)) {
			throw new BusinessException("Student Id does not exists!!");
		}
		studentRepository.deleteById(id);
		studentResponseBody.setStudents(studentRepository.findAll());
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");
		return studentResponseBody;
	}

}
