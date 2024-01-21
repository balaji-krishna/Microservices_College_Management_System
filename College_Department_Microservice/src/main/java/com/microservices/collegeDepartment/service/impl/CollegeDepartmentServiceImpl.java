package com.microservices.collegeDepartment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.collegeDepartment.entity.CollegeDepartment;
import com.microservices.collegeDepartment.model.ResponseBodyEntity;
import com.microservices.collegeDepartment.model.Student;
import com.microservices.collegeDepartment.repository.CollegeDepartmentRepository;
import com.microservices.collegeDepartment.service.CollegeDepartmentService;

@Service
public class CollegeDepartmentServiceImpl implements CollegeDepartmentService {

	@Autowired
	private CollegeDepartmentRepository collegeDepartmentRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public CollegeDepartment saveDepartment(CollegeDepartment collegeDepartment) {
		return collegeDepartmentRepository.save(collegeDepartment);
	}

	@Override
	public ResponseBodyEntity getDepartmentById(int id) {
		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		List<Student> students = new ArrayList<Student>();
		CollegeDepartment collegeDepartment = collegeDepartmentRepository.findById(id).get();

		students = restTemplate.getForObject("http://STUDENT-MICROSERVICE/student/getStudentByDepartmentId/" + id,
				List.class);

		responseBodyEntity.setCollegeDepartment(collegeDepartment);
		responseBodyEntity.setStudents(students);
		return responseBodyEntity;
	}

	@Override
	public List<ResponseBodyEntity> getAllDepartmentsInfo() {
		List<ResponseBodyEntity> responseBodyEntities = new ArrayList<ResponseBodyEntity>();
		List<CollegeDepartment> collegeDepartments = collegeDepartmentRepository.findAll();

		for (CollegeDepartment collegeDepartment : collegeDepartments) {	
			List<Student> students = new ArrayList<Student>();
			ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
			students = restTemplate.getForObject("http://STUDENT-MICROSERVICE/student/getStudentByDepartmentId/"
					+ collegeDepartment.getDepartmentId(), List.class);
			responseBodyEntity.setStudents(students);
			responseBodyEntity.setCollegeDepartment(collegeDepartment);
			responseBodyEntities.add(responseBodyEntity);
		}		
		
		return responseBodyEntities;
	}

}
