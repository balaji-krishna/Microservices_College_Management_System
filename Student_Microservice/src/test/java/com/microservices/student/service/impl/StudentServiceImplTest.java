package com.microservices.student.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservices.student.entity.Student;
import com.microservices.student.exception.BusinessException;
import com.microservices.student.httpData.StudentResponseBody;
import com.microservices.student.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
	
	@InjectMocks
	StudentServiceImpl studentServiceImpl;
	
	@Mock
	StudentRepository studentRepository;
	
	@Test
	void getStudentByIdSuccessTest() throws Exception {
		
		Student student = new Student();
		student.setId(1);
		student.setName("Balaji");
		student.setEmail("Balaji@gmail.com");
		student.setDepartmentId(1);
		
		Optional<Student> optional = Optional.ofNullable(student);
		
		when(studentRepository.existsById(1)).thenReturn(true);		
		when(studentRepository.findById(1)).thenReturn(optional);
		
		StudentResponseBody studentResponseBody = studentServiceImpl.getStudentById(1);
		
		assertTrue("01".equals(studentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(studentResponseBody.getResultMessage()));
		assertEquals(student, studentResponseBody.getStudent());
		verify(studentRepository, times(1)).existsById(1);
		verify(studentRepository, times(1)).findById(1);
		
	}
	
	@Test
	void getStudentByIdFailureTest() throws Exception {		
		when(studentRepository.existsById(1)).thenReturn(false); 
		assertThrows(BusinessException.class, ()-> studentServiceImpl.getStudentById(1));
		verify(studentRepository, times(1)).existsById(1);		
	}
	
	@Test
	void getAllStudentsInfoSuccessTest() throws Exception {
		
		Student student = new Student();
		student.setId(1);
		student.setName("Balaji");
		student.setEmail("Balaji@gmail.com");
		student.setDepartmentId(1);
		
		Student student2 = new Student();
		student2.setId(2);
		student2.setName("Harish");
		student2.setEmail("Harish@gmail.com");
		student2.setDepartmentId(2);
		
		List<Student> students = new ArrayList<>();
		students.add(student);
		students.add(student2);
			
		when(studentRepository.findAll()).thenReturn(students);
		
		StudentResponseBody studentResponseBody = studentServiceImpl.getAllStudentsInfo();
		
		assertTrue("01".equals(studentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(studentResponseBody.getResultMessage()));
		assertEquals(student, studentResponseBody.getStudents().get(0));
		assertEquals(student2, studentResponseBody.getStudents().get(1));
		verify(studentRepository, times(1)).findAll();
		
	}	
	
	@Test
	void saveStudentSuccessTest() throws Exception {
		
		Student student = new Student();
		student.setId(1);
		student.setName("Balaji");
		student.setEmail("Balaji@gmail.com");
		student.setDepartmentId(1);
		
		when(studentRepository.existsById(1)).thenReturn(false);
		when(studentRepository.save(student)).thenReturn(student);
		
		StudentResponseBody studentResponseBody = studentServiceImpl.saveStudent(student);
		
		assertTrue("01".equals(studentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(studentResponseBody.getResultMessage()));
		assertEquals(student, studentResponseBody.getStudent());
		verify(studentRepository, times(1)).existsById(1);
		verify(studentRepository, times(1)).save(student);
		
	}
	
	@Test
	void saveStudentFailureTest() throws Exception {		
		when(studentRepository.existsById(1)).thenReturn(true); 
		assertThrows(BusinessException.class, ()-> studentServiceImpl.saveStudent(new Student(1,"Balaji","Balaji@gmail.com",1)));
		verify(studentRepository, times(1)).existsById(1);		
	}
	
	@Test
	void updateStudentSuccessTest() throws Exception {
		
		Student student = new Student();
		student.setId(1);
		student.setName("Balaji");
		student.setEmail("Balaji@gmail.com");
		student.setDepartmentId(1);
		
		when(studentRepository.existsById(1)).thenReturn(true);
		when(studentRepository.save(student)).thenReturn(student);
		
		StudentResponseBody studentResponseBody = studentServiceImpl.updateStudent(student);
		
		assertTrue("01".equals(studentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(studentResponseBody.getResultMessage()));
		assertEquals(student, studentResponseBody.getStudent());
		verify(studentRepository, times(1)).existsById(1);
		verify(studentRepository, times(1)).save(student);
		
	}
	
	@Test
	void updateStudentFailureTest() throws Exception {		
		when(studentRepository.existsById(1)).thenReturn(false); 
		assertThrows(BusinessException.class, ()-> studentServiceImpl.updateStudent(new Student(1,"Balaji","Balaji@gmail.com",1)));
		verify(studentRepository, times(1)).existsById(1);		
	}
	
	@Test
	void deleteStudentByIdSuccessTest() throws Exception {
		
		Student student = new Student();
		student.setId(1);
		student.setName("Balaji");
		student.setEmail("Balaji@gmail.com");
		student.setDepartmentId(1);
		
		List<Student> students = new ArrayList<>();
		students.add(student);
		
		when(studentRepository.existsById(2)).thenReturn(true);
		when(studentRepository.findAll()).thenReturn(students);
		
		StudentResponseBody studentResponseBody = studentServiceImpl.deleteStudentById(2);
		
		assertTrue("01".equals(studentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(studentResponseBody.getResultMessage()));
		assertEquals(1, studentResponseBody.getStudents().get(0).getId());
		verify(studentRepository, times(1)).existsById(2);
		verify(studentRepository, times(1)).findAll();
		
	}
	
	@Test
	void deleteStudentByIdFailureTest() throws Exception {		
		when(studentRepository.existsById(1)).thenReturn(false); 
		assertThrows(BusinessException.class, ()-> studentServiceImpl.deleteStudentById(1));
		verify(studentRepository, times(1)).existsById(1);		
	}
	
	@Test
	void getStudentByDepartmentIdSuccessTest() throws Exception {
		
		Student student = new Student();
		student.setId(1);
		student.setName("Balaji");
		student.setEmail("Balaji@gmail.com");
		student.setDepartmentId(1);
		
		List<Student> students = new ArrayList<>();
		students.add(student);
			
		when(studentRepository.getStudentByDepartmentId(1)).thenReturn(students);
		when(studentRepository.findAll()).thenReturn(students);
		
		StudentResponseBody studentResponseBody = studentServiceImpl.getStudentByDepartmentId(1);
		
		assertTrue("01".equals(studentResponseBody.getResultCode()));
		assertTrue("Successfully completed".equals(studentResponseBody.getResultMessage()));
		assertEquals(1, studentResponseBody.getStudents().get(0).getId());
		verify(studentRepository, times(1)).findAll();
		verify(studentRepository, times(1)).getStudentByDepartmentId(1);
		
	}
	
	@Test
	void getStudentByDepartmentIdFailureTest() throws Exception {	
		Student student = new Student();
		student.setId(1);
		student.setName("Balaji");
		student.setEmail("Balaji@gmail.com");
		student.setDepartmentId(1);
		
		List<Student> students = new ArrayList<>();
		students.add(student);
		
		when(studentRepository.findAll()).thenReturn(students);
		
		assertThrows(BusinessException.class, ()-> studentServiceImpl.getStudentByDepartmentId(2));
		verify(studentRepository, times(1)).findAll();		
	}
	
}
