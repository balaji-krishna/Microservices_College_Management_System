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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.collegeDepartment.constant.ApplicationConstant;
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
	public ResponseBodyEntity getAllDepartmentsInfo(@RequestHeader(value = ApplicationConstant.X_CORRELATION_ID) String correlationId)
			throws Exception {
		LOGGER.info("The api /getAllDepartmentsInfo is called with X-Correlation-Id: {}", correlationId);
		return collegeDepartmentService.getAllDepartmentsInfo(correlationId);
	}

	@GetMapping(path = "getDepartmentById/{id}")
	public ResponseBodyEntity getDepartmentById(@PathVariable int id,
			@RequestHeader(value = ApplicationConstant.X_CORRELATION_ID) String correlationId) throws Exception {
		LOGGER.info("The api /getDepartmentById/{} is called with X-Correlation-Id: {}", id, correlationId);
		return collegeDepartmentService.getDepartmentById(id, correlationId);
	}

	@PostMapping(path = "saveDepartment")
	public ResponseBodyEntity saveDepartment(@RequestBody CollegeDepartment collegeDepartment,
			@RequestHeader(value = ApplicationConstant.X_CORRELATION_ID) String correlationId) throws Exception {
		LOGGER.info("The api /saveDepartment is called with X-Correlation-Id: {}", correlationId);
		return collegeDepartmentService.saveDepartment(collegeDepartment);
	}

	@PutMapping(path = "updateDepartment")
	public ResponseBodyEntity updateDepartment(@RequestBody CollegeDepartment collegeDepartment,
			@RequestHeader(value = ApplicationConstant.X_CORRELATION_ID) String correlationId) throws Exception {
		LOGGER.info("The api /updateDepartment is called with X-Correlation-Id: {}", correlationId);
		return collegeDepartmentService.updateDepartment(collegeDepartment);
	}

	@DeleteMapping(path = "deleteDepartmentById/{id}")
	public ResponseBodyEntity deleteDepartmentById(@PathVariable int id,
			@RequestHeader(value = ApplicationConstant.X_CORRELATION_ID) String correlationId) throws Exception {
		LOGGER.info("The api /deleteDepartmentById/{} is called with X-Correlation-Id: {}", id, correlationId);
		return collegeDepartmentService.deleteDepartmentById(id);
	}

}
