package com.microservices.collegeDepartment.service;

import com.microservices.collegeDepartment.entity.Teachers;
import com.microservices.collegeDepartment.httpData.TeachersResponse;

public interface TeacherService {

	TeachersResponse saveTeacher(Teachers teachers) throws Exception;

	TeachersResponse updateTeacher(Teachers teachers) throws Exception;

	TeachersResponse deleteTeacherById(int id) throws Exception;

	TeachersResponse getAllTeachersInfo() throws Exception;

	TeachersResponse getTeacherById(int id) throws Exception;

}
