package com.microservices.collegeDepartment.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservices.collegeDepartment.entity.Teachers;
import com.microservices.collegeDepartment.httpData.TeachersResponse;
import com.microservices.collegeDepartment.service.TeacherService;

@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {

	@InjectMocks
	TeacherController testController;

	@Mock
	TeacherService teacherService;

	@Test
	void getAllTeachersInfoSuccessTest() throws Exception {

		TeachersResponse teachersResponse = new TeachersResponse();
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");

		when(teacherService.getAllTeachersInfo()).thenReturn(teachersResponse);

		TeachersResponse actualTeachersResponse = testController.getAllTeachersInfo();

		assertTrue("01".equals(actualTeachersResponse.getResultCode()));
		assertTrue("Successfully completed".equals(actualTeachersResponse.getResultMessage()));
		verify(teacherService, times(1)).getAllTeachersInfo();

	}

	@Test
	void getStudentByIdSuccessTest() throws Exception {

		TeachersResponse teachersResponse = new TeachersResponse();
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");

		when(teacherService.getTeacherById(1)).thenReturn(teachersResponse);

		TeachersResponse actualTeachersResponse = testController.getTeacherById(1);

		assertTrue("01".equals(actualTeachersResponse.getResultCode()));
		assertTrue("Successfully completed".equals(actualTeachersResponse.getResultMessage()));
		verify(teacherService, times(1)).getTeacherById(1);
	}

	@Test
	void saveStudentSuccessTest() throws Exception {

		Teachers teachers = new Teachers();
		teachers.setTeacherId(1);
		teachers.setTeacherName("Balaji");
		teachers.setTeacherDepartment("Mechatronics");

		TeachersResponse teachersResponse = new TeachersResponse();
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");
		teachersResponse.setTeachers(teachers);

		when(teacherService.saveTeacher(teachers)).thenReturn(teachersResponse);

		TeachersResponse actualTeachersResponse = testController.saveTeacher(teachers);

		assertTrue("01".equals(actualTeachersResponse.getResultCode()));
		assertTrue("Successfully completed".equals(actualTeachersResponse.getResultMessage()));
		verify(teacherService, times(1)).saveTeacher(teachers);
	}
	
	@Test
	void updateStudentSuccessTest() throws Exception {

		Teachers teachers = new Teachers();
		teachers.setTeacherId(1);
		teachers.setTeacherName("Balaji");
		teachers.setTeacherDepartment("Mechatronics");

		TeachersResponse teachersResponse = new TeachersResponse();
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");
		teachersResponse.setTeachers(teachers);

		when(teacherService.updateTeacher(teachers)).thenReturn(teachersResponse);

		TeachersResponse actualTeachersResponse = testController.updateTeacher(teachers);

		assertTrue("01".equals(actualTeachersResponse.getResultCode()));
		assertTrue("Successfully completed".equals(actualTeachersResponse.getResultMessage()));
		verify(teacherService, times(1)).updateTeacher(teachers);
	}
	
	@Test
	void deleteTeacherByIdSuccessTest() throws Exception {

		TeachersResponse teachersResponse = new TeachersResponse();
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");

		when(teacherService.deleteTeacherById(1)).thenReturn(teachersResponse);

		TeachersResponse actualTeachersResponse = testController.deleteTeacherById(1);

		assertTrue("01".equals(actualTeachersResponse.getResultCode()));
		assertTrue("Successfully completed".equals(actualTeachersResponse.getResultMessage()));
		verify(teacherService, times(1)).deleteTeacherById(1);
	}

}
