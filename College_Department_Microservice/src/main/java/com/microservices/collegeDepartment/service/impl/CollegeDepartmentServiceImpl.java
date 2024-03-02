package com.microservices.collegeDepartment.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.microservices.collegeDepartment.entity.CollegeDepartment;
import com.microservices.collegeDepartment.exception.BusinessException;
import com.microservices.collegeDepartment.httpData.CollegeDepartmentResponse;
import com.microservices.collegeDepartment.httpData.StudentResponseBody;
import com.microservices.collegeDepartment.model.ResponseBodyEntity;
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

	@Value("${profile.demo.message}")
	public String springProfilesDemo;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ResponseBodyEntity saveDepartment(CollegeDepartment collegeDepartment) throws Exception {
		LOGGER.info("The method saveDepartment is called with id {}", collegeDepartment.getDepartmentId());
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		CollegeDepartmentResponse collegeDepartmentResponse = new CollegeDepartmentResponse();
		if (collegeDepartmentRepository.existsById(collegeDepartment.getDepartmentId())) {
			throw new BusinessException("Department Id already exists!!");
		}
		collegeDepartmentResponse.setCollegeDepartment(collegeDepartmentRepository.save(collegeDepartment));
		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");
		responseBodyEntity.setCollegeDepartmentResponse(collegeDepartmentResponse);
		if (responseBodyEntity.getResultCode().equals("01")) {
			throw new BusinessException("Test exception");
		}
		LOGGER.info("The method saveDepartment is successfully completed");
		return responseBodyEntity;
	}

	@Override
	@CircuitBreaker(name = COLLEGE_DEPT_SERVICE, fallbackMethod = "getDepartmentByIdFallback")
	public ResponseBodyEntity getDepartmentById(int id, String correlationId) throws Exception {
		LOGGER.info("The method getDepartmentById is called with id {}", id);
		CollegeDepartmentResponse collegeDepartmentResponse = new CollegeDepartmentResponse();
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		if (!collegeDepartmentRepository.existsById(id)) {
			throw new BusinessException("College Id does not exists!!");
		}
		collegeDepartmentResponse.setCollegeDepartment(collegeDepartmentRepository.findById(id).get());
		LOGGER.info("THE STUDENT MICROSERVICE IS CALLED");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Correlation-Id", correlationId);

		HttpEntity request = new HttpEntity(headers);

		studentResponseBody = restTemplate.exchange(STUDENT_MICROSERVICE_BASE_URL + "getStudentByDepartmentId/" + id,
				HttpMethod.GET, request, StudentResponseBody.class).getBody();

		if (studentResponseBody != null && studentResponseBody.getResultCode().equals("01")
				&& !studentResponseBody.getStudents().isEmpty()) {
			collegeDepartmentResponse.setStudents(studentResponseBody.getStudents());
		} else {
			throw new BusinessException("Error in student data");
		}
		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");
		responseBodyEntity.setStudentServiceStatus("UP");
		responseBodyEntity.setCollegeDepartmentResponse(collegeDepartmentResponse);
		LOGGER.info("The method getDepartmentById is successfully completed");
		return responseBodyEntity;
	}

	public ResponseBodyEntity getDepartmentByIdFallback(int id, String correlationId, RestClientException exception) {
		LOGGER.info("THE STUDENT MICROSERVICE IS DOWN");
		LOGGER.info("The method getDepartmentByIdFallback is called with id {}", id);
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		CollegeDepartmentResponse collegeDepartmentResponse = new CollegeDepartmentResponse();
		CollegeDepartment collegeDepartment = collegeDepartmentRepository.findById(id).get();

		collegeDepartmentResponse.setCollegeDepartment(collegeDepartment);
		responseBodyEntity.setCollegeDepartmentResponse(collegeDepartmentResponse);
		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");
		responseBodyEntity.setStudentServiceStatus("DOWN");
		LOGGER.info("The method getDepartmentByIdFallback is successfully completed");
		return responseBodyEntity;
	}

	@Override
	@Retry(name = COLLEGE_DEPT_SERVICE, fallbackMethod = "getAllDepartmentsInfoFallback")
	public ResponseBodyEntity getAllDepartmentsInfo(String correlationId) throws Exception {
		LOGGER.info("The method getAllDepartmentsInfo is called");
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		CollegeDepartmentResponse collegeDepartmentResponse = new CollegeDepartmentResponse();
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		List<CollegeDepartment> collegeDepartments = collegeDepartmentRepository.findAll();
		collegeDepartmentResponse.setCollegeDepartments(collegeDepartments);

		LOGGER.info("THE STUDENT MICROSERVICE IS CALLED");
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Correlation-Id", correlationId);

		HttpEntity request = new HttpEntity(headers);

		studentResponseBody = restTemplate.exchange(STUDENT_MICROSERVICE_BASE_URL + "getAllStudentsInfo",
				HttpMethod.GET, request, StudentResponseBody.class).getBody();

		if (studentResponseBody != null && studentResponseBody.getResultCode().equals("01")
				&& !studentResponseBody.getStudents().isEmpty()) {
			collegeDepartmentResponse.setStudents(studentResponseBody.getStudents());
		} else {
			throw new BusinessException("Error getting student details");
		}

		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");
		responseBodyEntity.setStudentServiceStatus("UP");
		responseBodyEntity.setCollegeDepartmentResponse(collegeDepartmentResponse);
		LOGGER.info("The method getAllDepartmentsInfo is successfully completed");
		return responseBodyEntity;
	}

	public ResponseBodyEntity getAllDepartmentsInfoFallback(String correlationId, Exception exception) {
		LOGGER.info("THE STUDENT MICROSERVICE IS DOWN");
		LOGGER.info("The method getAllDepartmentsInfoFallback is called");
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		CollegeDepartmentResponse collegeDepartmentResponse = new CollegeDepartmentResponse();
		List<CollegeDepartment> collegeDepartments = collegeDepartmentRepository.findAll();
		collegeDepartmentResponse.setCollegeDepartments(collegeDepartments);

		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");
		responseBodyEntity.setStudentServiceStatus("DOWN");
		responseBodyEntity.setCollegeDepartmentResponse(collegeDepartmentResponse);
		LOGGER.info("The method getAllDepartmentsInfoFallback is successfully completed");
		return responseBodyEntity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ResponseBodyEntity updateDepartment(CollegeDepartment collegeDepartment) throws Exception {
		LOGGER.info("The method updateDepartment is called with id {}", collegeDepartment.getDepartmentId());
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		CollegeDepartmentResponse collegeDepartmentResponse = new CollegeDepartmentResponse();
		if (!collegeDepartmentRepository.existsById(collegeDepartment.getDepartmentId())) {
			throw new BusinessException("Department Id does not exists!!");
		}
		collegeDepartmentResponse.setCollegeDepartment(collegeDepartmentRepository.save(collegeDepartment));
		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");
		responseBodyEntity.setCollegeDepartmentResponse(collegeDepartmentResponse);
		if (responseBodyEntity.getResultCode().equals("01")) {
			throw new BusinessException("Test exception");
		}
		LOGGER.info("The method updateDepartment is successfully completed");
		return responseBodyEntity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ResponseBodyEntity deleteDepartmentById(int id) throws Exception {
		LOGGER.info("The method deleteDepartmentById is called with id {}", id);
		CollegeDepartmentResponse collegeDepartmentResponse = new CollegeDepartmentResponse();
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		if (!collegeDepartmentRepository.existsById(id)) {
			throw new BusinessException("College Id does not exists!!");
		}
		collegeDepartmentRepository.deleteById(id);
		List<CollegeDepartment> collegeDepartments = collegeDepartmentRepository.findAll();
		collegeDepartmentResponse.setCollegeDepartments(collegeDepartments);
		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");
		responseBodyEntity.setCollegeDepartmentResponse(collegeDepartmentResponse);
		if (responseBodyEntity.getResultCode().equals("01")) {
			throw new BusinessException("Test exception");
		}
		LOGGER.info("The method deleteDepartmentById is successfully completed");
		return responseBodyEntity;
	}

	@Override
	public String springProfilesDemo() {
		return springProfilesDemo;
	}

}
