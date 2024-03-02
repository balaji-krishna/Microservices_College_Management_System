package com.microservices.collegeDepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.collegeDepartment.entity.Teachers;

public interface TeachersRepository extends JpaRepository<Teachers, Integer> {

}
