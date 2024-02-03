package com.microservices.student.service;

import com.microservices.student.entity.Student;
import com.microservices.student.httpData.StudentResponseBody;

public interface StudentService {

	StudentResponseBody getStudentById(int id) throws Exception;

	StudentResponseBody getAllStudentsInfo() throws Exception;

	StudentResponseBody saveStudent(Student student) throws Exception;

	StudentResponseBody getStudentByDepartmentId(int id) throws Exception;

	StudentResponseBody updateStudent(Student student) throws Exception;

	StudentResponseBody deleteStudentById(int id) throws Exception;

}
