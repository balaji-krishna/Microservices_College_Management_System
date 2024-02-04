package com.microservices.collegeDepartment.service;

import com.microservices.collegeDepartment.entity.CollegeDepartment;
import com.microservices.collegeDepartment.model.ResponseBodyEntity;

public interface CollegeDepartmentService {

	ResponseBodyEntity saveDepartment(CollegeDepartment collegeDepartment)  throws Exception;

	ResponseBodyEntity getDepartmentById(int id) throws Exception;
	
	ResponseBodyEntity getAllDepartmentsInfo() throws Exception;

	ResponseBodyEntity updateDepartment(CollegeDepartment collegeDepartment) throws Exception;

	ResponseBodyEntity deleteDepartmentById(int id) throws Exception;

}
