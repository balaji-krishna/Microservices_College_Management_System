package com.microservices.student.service;

import java.util.List;

import com.microservices.student.entity.Student;

public interface StudentService {

	Student getStudentById(int id);

	List<Student> getAllStudentsInfo();

	Student saveStudent(Student student);

	List<Student> getStudentByDepartmentId(int id);

}
