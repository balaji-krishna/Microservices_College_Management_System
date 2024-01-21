package com.microservices.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	List<Student> getStudentByDepartmentId(int id);

}
