package com.microservices.student.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservices.student.entity.Student;
import com.microservices.student.httpData.StudentResponseBody;
import com.microservices.student.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
	
	@InjectMocks
	StudentController studentController;
	
	@Mock
	StudentService studentService;
	
	@Test
	void getAllStudentsInfoSuccessTest() throws Exception {
		
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");
			
		when(studentService.getAllStudentsInfo()).thenReturn(studentResponseBody);
		
		StudentResponseBody actualStudentResponseBody = studentController.getAllStudentsInfo("1");
		
		assertTrue("01".equals(actualStudentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(actualStudentResponseBody.getResultMessage()));
		verify(studentService, times(1)).getAllStudentsInfo();
	}
	
	@Test
	void getStudentByIdSuccessTest() throws Exception {
		
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");
			
		when(studentService.getStudentById(1)).thenReturn(studentResponseBody);
		
		StudentResponseBody actualStudentResponseBody = studentController.getStudentById(1, "1");
		
		assertTrue("01".equals(actualStudentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(actualStudentResponseBody.getResultMessage()));
		verify(studentService, times(1)).getStudentById(1);
	}	
	
	@Test
	void saveStudentSuccessTest() throws Exception {
		
		Student student = new Student();
		student.setId(1);
		student.setName("Balaji");
		student.setEmail("Balaji@gmail.com");
		student.setDepartmentId(1);
		
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");
		studentResponseBody.setStudent(student);
		
		when(studentService.saveStudent(student)).thenReturn(studentResponseBody);
		
		StudentResponseBody actualStudentResponseBody = studentController.saveStudent(student, "1");
		
		assertTrue("01".equals(actualStudentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(actualStudentResponseBody.getResultMessage()));
		verify(studentService, times(1)).saveStudent(student);
	}	
	
	@Test
	void getStudentByDepartmentIdSuccessTest() throws Exception {
		
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");
			
		when(studentService.getStudentByDepartmentId(1)).thenReturn(studentResponseBody);
		
		StudentResponseBody actualStudentResponseBody = studentController.getStudentByDepartmentId(1, "1");
		
		assertTrue("01".equals(actualStudentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(actualStudentResponseBody.getResultMessage()));
		verify(studentService, times(1)).getStudentByDepartmentId(1);
	}	
	
	@Test
	void updateStudentSuccessTest() throws Exception {
		
		Student student = new Student();
		student.setId(1);
		student.setName("Balaji");
		student.setEmail("Balaji@gmail.com");
		student.setDepartmentId(1);
		
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");
		studentResponseBody.setStudent(student);
		
		when(studentService.updateStudent(student)).thenReturn(studentResponseBody);
		
		StudentResponseBody actualStudentResponseBody = studentController.updateStudent(student, "1");
		
		assertTrue("01".equals(actualStudentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(actualStudentResponseBody.getResultMessage()));
		verify(studentService, times(1)).updateStudent(student);
	}	
	
	@Test
	void deleteStudentByIdSuccessTest() throws Exception {
		
		StudentResponseBody studentResponseBody = new StudentResponseBody();
		studentResponseBody.setResultCode("01");
		studentResponseBody.setResultMessage("Successfully completed");
			
		when(studentService.deleteStudentById(1)).thenReturn(studentResponseBody);
		
		StudentResponseBody actualStudentResponseBody = studentController.deleteStudentById(1, "1");
		
		assertTrue("01".equals(actualStudentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(actualStudentResponseBody.getResultMessage()));
		verify(studentService, times(1)).deleteStudentById(1);
	}	

}
