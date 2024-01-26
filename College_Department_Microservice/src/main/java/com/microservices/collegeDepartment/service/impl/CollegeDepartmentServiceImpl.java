package com.microservices.collegeDepartment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.collegeDepartment.entity.CollegeDepartment;
import com.microservices.collegeDepartment.model.ResponseBodyEntity;
import com.microservices.collegeDepartment.model.Student;
import com.microservices.collegeDepartment.repository.CollegeDepartmentRepository;
import com.microservices.collegeDepartment.service.CollegeDepartmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class CollegeDepartmentServiceImpl implements CollegeDepartmentService {

	@Autowired
	private CollegeDepartmentRepository collegeDepartmentRepository;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CollegeDepartmentServiceImpl.class);
	
	public static final String COLLEGE_DEPT_SERVICE = "collegeDeptService";
	
	public static final String STUDENT_MICROSERVICE_BASE_URL = "http://STUDENT-MICROSERVICE/student/";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public CollegeDepartment saveDepartment(CollegeDepartment collegeDepartment) {
		return collegeDepartmentRepository.save(collegeDepartment);
	}

	@Override
	@CircuitBreaker(name = COLLEGE_DEPT_SERVICE, fallbackMethod = "getDepartmentByIdFallback")
	@Retry(name = COLLEGE_DEPT_SERVICE, fallbackMethod = "getDepartmentByIdFallback")
	public ResponseBodyEntity getDepartmentById(int id) {
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		List<Student> students = new ArrayList<Student>();
		CollegeDepartment collegeDepartment = collegeDepartmentRepository.findById(id).get();

		LOGGER.info("THE STUDENT MICROSERVICE IS CALLED");
		students = restTemplate.getForObject(STUDENT_MICROSERVICE_BASE_URL + "getStudentByDepartmentId/" + id,
				List.class);

		responseBodyEntity.setCollegeDepartment(collegeDepartment);
		responseBodyEntity.setStudents(students);
		return responseBodyEntity;
	}
	
	public ResponseBodyEntity getDepartmentByIdFallback(int id, Exception exception) {
		LOGGER.info("THE STUDENT MICROSERVICE IS DOWN");
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		CollegeDepartment collegeDepartment = collegeDepartmentRepository.findById(id).get();

		responseBodyEntity.setCollegeDepartment(collegeDepartment);
		return responseBodyEntity;
	}

	@Override
	@Retry(name = COLLEGE_DEPT_SERVICE, fallbackMethod = "getAllDepartmentsInfoFallback")
	public List<ResponseBodyEntity> getAllDepartmentsInfo() {
		List<ResponseBodyEntity> responseBodyEntities = new ArrayList<ResponseBodyEntity>();
		List<CollegeDepartment> collegeDepartments = collegeDepartmentRepository.findAll();

		for (CollegeDepartment collegeDepartment : collegeDepartments) {	
			List<Student> students = new ArrayList<Student>();
			ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
			LOGGER.info("THE STUDENT MICROSERVICE IS CALLED");
			students = restTemplate.getForObject(STUDENT_MICROSERVICE_BASE_URL + "getStudentByDepartmentId/"
					+ collegeDepartment.getDepartmentId(), List.class);
			responseBodyEntity.setStudents(students);
			responseBodyEntity.setCollegeDepartment(collegeDepartment);
			responseBodyEntities.add(responseBodyEntity);
		}		
		
		return responseBodyEntities;
	}
	
	public List<ResponseBodyEntity> getAllDepartmentsInfoFallback(Exception exception) {
		LOGGER.info("THE STUDENT MICROSERVICE IS DOWN");	
		List<ResponseBodyEntity> responseBodyEntities = new ArrayList<ResponseBodyEntity>();
		List<CollegeDepartment> collegeDepartments = collegeDepartmentRepository.findAll();

		for (CollegeDepartment collegeDepartment : collegeDepartments) {
			ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
			responseBodyEntity.setCollegeDepartment(collegeDepartment);
			responseBodyEntities.add(responseBodyEntity);
		}		
		
		return responseBodyEntities;
	}

}
