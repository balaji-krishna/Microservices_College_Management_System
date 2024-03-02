package com.microservices.collegeDepartment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.collegeDepartment.entity.Teachers;
import com.microservices.collegeDepartment.exception.BusinessException;
import com.microservices.collegeDepartment.httpData.TeachersResponse;
import com.microservices.collegeDepartment.repository.TeachersRepository;
import com.microservices.collegeDepartment.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeachersRepository teachersRepository;
	
	@Value("${profile.completion.message}")
	public String profileCompletionMessage;

	@Override
	public TeachersResponse getTeacherById(int id) throws Exception {
		TeachersResponse teachersResponse = new TeachersResponse();
		if (!teachersRepository.existsById(id)) {
			throw new BusinessException("Teacher Id does not exists!!");
		}
		teachersResponse.setTeachers(teachersRepository.findById(id).get());
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");
		teachersResponse.setProfileName(profileCompletionMessage);
		return teachersResponse;
	}

	@Override
	public TeachersResponse getAllTeachersInfo() throws Exception {
		TeachersResponse teachersResponse = new TeachersResponse();
		try {
			teachersResponse.setTeachersList(teachersRepository.findAll());
			teachersResponse.setResultCode("01");
			teachersResponse.setResultMessage("Successfully completed");
			teachersResponse.setProfileName(profileCompletionMessage);
		} catch (Exception e) {
			throw new BusinessException("Exception occurred");
		}
		return teachersResponse;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public TeachersResponse deleteTeacherById(int id) throws Exception {
		TeachersResponse teachersResponse = new TeachersResponse();
		if (!teachersRepository.existsById(id)) {
			throw new BusinessException("Teacher Id does not exists!!");
		}
		teachersRepository.deleteById(id);
		teachersResponse.setTeachersList(teachersRepository.findAll());
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");
		teachersResponse.setProfileName(profileCompletionMessage);
		return teachersResponse;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public TeachersResponse updateTeacher(Teachers teachers) throws Exception {
		TeachersResponse teachersResponse = new TeachersResponse();
		if (!teachersRepository.existsById(teachers.getTeacherId())) {
			throw new BusinessException("Teacher Id does not exists!!");
		}
		teachersResponse.setTeachers(teachersRepository.save(teachers));
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");
		teachersResponse.setProfileName(profileCompletionMessage);
		return teachersResponse;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public TeachersResponse saveTeacher(Teachers teachers) throws Exception {
		TeachersResponse teachersResponse = new TeachersResponse();
		if (teachersRepository.existsById(teachers.getTeacherId())) {
			throw new BusinessException("Teacher Id already exists!!");
		}
		teachersResponse.setTeachers(teachersRepository.save(teachers));
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");
		teachersResponse.setProfileName(profileCompletionMessage);
		return teachersResponse;
	}

}
