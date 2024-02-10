package com.microservices.collegeDepartment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.collegeDepartment.entity.CollegeDepartment;
import com.microservices.collegeDepartment.model.ResponseBodyEntity;
import com.microservices.collegeDepartment.service.CollegeDepartmentService;

@RestController
@RequestMapping(path = "department")
public class CollegeDepartmentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CollegeDepartmentController.class);
	
	@Autowired
	private CollegeDepartmentService collegeDepartmentService;
	
	@GetMapping(path = "getAllDepartmentsInfo")
	public ResponseBodyEntity getAllDepartmentsInfo() throws Exception {
		LOGGER.info("The api /getAllDepartmentsInfo is called");
		return collegeDepartmentService.getAllDepartmentsInfo();
	}
	
	@GetMapping(path = "getDepartmentById/{id}")
	public ResponseBodyEntity getDepartmentById(@PathVariable int id) throws Exception {
		LOGGER.info("The api /getDepartmentById/{} is called", id);
		return collegeDepartmentService.getDepartmentById(id);
	}
	
	@PostMapping(path = "saveDepartment")
	public ResponseBodyEntity saveDepartment(@RequestBody CollegeDepartment collegeDepartment) throws Exception {
		LOGGER.info("The api /saveDepartment is called");
		return collegeDepartmentService.saveDepartment(collegeDepartment);
	}
	
	@PutMapping(path = "updateDepartment")
	public ResponseBodyEntity updateDepartment(@RequestBody CollegeDepartment collegeDepartment) throws Exception {
		LOGGER.info("The api /updateDepartment is called");
		return collegeDepartmentService.updateDepartment(collegeDepartment);
	}
	
	@DeleteMapping(path = "deleteDepartmentById/{id}")
	public ResponseBodyEntity deleteDepartmentById(@PathVariable int id) throws Exception {	
		LOGGER.info("The api /deleteDepartmentById/{} is called", id);
		return collegeDepartmentService.deleteDepartmentById(id);
	}

}
