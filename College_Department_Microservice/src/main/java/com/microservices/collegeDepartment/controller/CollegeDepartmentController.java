package com.microservices.collegeDepartment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.collegeDepartment.entity.CollegeDepartment;
import com.microservices.collegeDepartment.model.ResponseBodyEntity;
import com.microservices.collegeDepartment.service.CollegeDepartmentService;

@RestController
@RequestMapping(path = "department")
public class CollegeDepartmentController {
	
	@Autowired
	private CollegeDepartmentService collegeDepartmentService;
	
	@GetMapping(path = "getAllDepartmentsInfo")
	public List<ResponseBodyEntity> getAllDepartmentsInfo() {
		return collegeDepartmentService.getAllDepartmentsInfo();
	}
	
	@GetMapping(path = "getDepartmentById/{id}")
	public ResponseBodyEntity getDepartmentById(@PathVariable int id) {	
		return collegeDepartmentService.getDepartmentById(id);
	}
	
	@PostMapping(path = "saveDepartment")
	public CollegeDepartment saveDepartment(@RequestBody CollegeDepartment collegeDepartment) {
		return collegeDepartmentService.saveDepartment(collegeDepartment);
	}

}
