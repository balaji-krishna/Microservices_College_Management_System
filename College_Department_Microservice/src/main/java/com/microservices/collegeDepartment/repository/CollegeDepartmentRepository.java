package com.microservices.collegeDepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.collegeDepartment.entity.CollegeDepartment;

public interface CollegeDepartmentRepository extends JpaRepository<CollegeDepartment, Integer> {

}
