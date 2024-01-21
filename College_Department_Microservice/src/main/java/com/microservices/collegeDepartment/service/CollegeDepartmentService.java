package com.microservices.collegeDepartment.service;

import java.util.List;

import com.microservices.collegeDepartment.entity.CollegeDepartment;
import com.microservices.collegeDepartment.model.ResponseBodyEntity;

public interface CollegeDepartmentService {

	CollegeDepartment saveDepartment(CollegeDepartment collegeDepartment);

	ResponseBodyEntity getDepartmentById(int id);
	
	List<ResponseBodyEntity> getAllDepartmentsInfo();

}
