package com.microservices.collegeDepartment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.collegeDepartment.entity.Teachers;
import com.microservices.collegeDepartment.httpData.TeachersResponse;
import com.microservices.collegeDepartment.service.TeacherService;

@RestController
@RequestMapping(path = "teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@PostMapping(path = "saveTeacher")
	public TeachersResponse saveTeacher(@RequestBody Teachers teachers) throws Exception {
		return teacherService.saveTeacher(teachers);
	}

	@PutMapping(path = "updateTeacher")
	public TeachersResponse updateTeacher(@RequestBody Teachers teachers) throws Exception {
		return teacherService.updateTeacher(teachers);
	}

	@DeleteMapping(path = "deleteTeacherById/{id}")
	public TeachersResponse deleteTeacherById(@PathVariable int id) throws Exception {
		return teacherService.deleteTeacherById(id);
	}
	
	@GetMapping(path = "getAllTeachersInfo")
	public TeachersResponse getAllTeachersInfo()
			throws Exception {
		return teacherService.getAllTeachersInfo();
	}

	@GetMapping(path = "getTeacherById/{id}")
	public TeachersResponse getTeacherById(@PathVariable int id) throws Exception {
		return teacherService.getTeacherById(id);
	}
}
