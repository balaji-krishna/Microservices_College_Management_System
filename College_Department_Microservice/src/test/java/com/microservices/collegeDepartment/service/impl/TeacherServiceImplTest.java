package com.microservices.collegeDepartment.service.impl;

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

import com.microservices.collegeDepartment.entity.Teachers;
import com.microservices.collegeDepartment.exception.BusinessException;
import com.microservices.collegeDepartment.httpData.TeachersResponse;
import com.microservices.collegeDepartment.repository.TeachersRepository;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceImplTest {

	@InjectMocks
	TeacherServiceImpl teacherServiceImpl;

	@Mock
	TeachersRepository teachersRepository;

	@Test
	void getAllTeachersInfoSuccessTest() throws Exception {

		Teachers teachers = new Teachers();
		teachers.setTeacherId(1);
		teachers.setTeacherName("Balaji");
		teachers.setTeacherDepartment("Mechatronics");

		Teachers teachers2 = new Teachers();
		teachers2.setTeacherId(2);
		teachers2.setTeacherName("Annamalai");
		teachers2.setTeacherDepartment("Mechatronics");

		List<Teachers> teachersList = new ArrayList<>();
		teachersList.add(teachers);
		teachersList.add(teachers2);

		TeachersResponse teachersResponse = new TeachersResponse();
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");
		teachersResponse.setTeachersList(teachersList);

		when(teachersRepository.findAll()).thenReturn(teachersList);

		TeachersResponse actualTeachersResponse = teacherServiceImpl.getAllTeachersInfo();

		assertTrue("01".equals(actualTeachersResponse.getResultCode()));
		assertTrue("Successfully completed".equals(actualTeachersResponse.getResultMessage()));
		verify(teachersRepository, times(1)).findAll();

	}

	@Test
	void getTeacherByIdSuccessTest() throws Exception {

		Teachers teachers = new Teachers();
		teachers.setTeacherId(1);
		teachers.setTeacherName("Balaji");
		teachers.setTeacherDepartment("Mechatronics");

		TeachersResponse teachersResponse = new TeachersResponse();
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");

		Optional<Teachers> optional = Optional.ofNullable(teachers);

		when(teachersRepository.findById(1)).thenReturn(optional);
		when(teachersRepository.existsById(1)).thenReturn(true);

		TeachersResponse actualTeachersResponse = teacherServiceImpl.getTeacherById(1);

		assertTrue("01".equals(actualTeachersResponse.getResultCode()));
		assertTrue("Successfully completed".equals(actualTeachersResponse.getResultMessage()));
		verify(teachersRepository, times(1)).findById(1);
		verify(teachersRepository, times(1)).existsById(1);

	}

	@Test
	void deleteTeacherByIdFailureTest() throws Exception {
		when(teachersRepository.existsById(1)).thenReturn(false);
		assertThrows(BusinessException.class, ()-> teacherServiceImpl.getTeacherById(1));
		verify(teachersRepository, times(1)).existsById(1);

	}

	@Test
	void deleteTeacherByIdSuccessTest() throws Exception {

		Teachers teachers = new Teachers();
		teachers.setTeacherId(1);
		teachers.setTeacherName("Balaji");
		teachers.setTeacherDepartment("Mechatronics");

		List<Teachers> teachersList = new ArrayList<>();
		teachersList.add(teachers);

		TeachersResponse teachersResponse = new TeachersResponse();
		teachersResponse.setResultCode("01");
		teachersResponse.setResultMessage("Successfully completed");

		when(teachersRepository.findAll()).thenReturn(teachersList);
		when(teachersRepository.existsById(2)).thenReturn(true);

		TeachersResponse actualTeachersResponse = teacherServiceImpl.deleteTeacherById(2);

		assertTrue("01".equals(actualTeachersResponse.getResultCode()));
		assertTrue("Successfully completed".equals(actualTeachersResponse.getResultMessage()));
		assertEquals(1, actualTeachersResponse.getTeachersList().get(0).getTeacherId());
		verify(teachersRepository, times(1)).findAll();
		verify(teachersRepository, times(1)).existsById(2);

	}

	@Test
	void getTeacherByIdFailureTest() throws Exception {
		when(teachersRepository.existsById(1)).thenReturn(false);
		assertThrows(BusinessException.class, ()-> teacherServiceImpl.deleteTeacherById(1));
		verify(teachersRepository, times(1)).existsById(1);

	}

	@Test
	void updateTeacherSuccessTest() throws Exception {

		Teachers teachers = new Teachers();
		teachers.setTeacherId(1);
		teachers.setTeacherName("Balaji");
		teachers.setTeacherDepartment("Mechatronics");

		when(teachersRepository.existsById(1)).thenReturn(true);
		when(teachersRepository.save(teachers)).thenReturn(teachers);

		TeachersResponse actualTeachersResponse = teacherServiceImpl.updateTeacher(teachers);

		assertTrue("01".equals(actualTeachersResponse.getResultCode()));
		assertTrue("Successfully completed".equals(actualTeachersResponse.getResultMessage()));
		assertEquals(teachers, actualTeachersResponse.getTeachers());
		verify(teachersRepository, times(1)).existsById(1);
		verify(teachersRepository, times(1)).save(teachers);

	}

	@Test
	void updateTeacherFailureTest() throws Exception {		
		when(teachersRepository.existsById(1)).thenReturn(false); 
		assertThrows(BusinessException.class, ()-> teacherServiceImpl.updateTeacher(new Teachers(1,"Balaji","Mechatronics")));
		verify(teachersRepository, times(1)).existsById(1);		
	}

	@Test
	void saveTeacherSuccessTest() throws Exception {

		Teachers teachers = new Teachers();
		teachers.setTeacherId(1);
		teachers.setTeacherName("Balaji");
		teachers.setTeacherDepartment("Mechatronics");

		when(teachersRepository.existsById(1)).thenReturn(false);
		when(teachersRepository.save(teachers)).thenReturn(teachers);

		TeachersResponse actualTeachersResponse = teacherServiceImpl.saveTeacher(teachers);

		assertTrue("01".equals(actualTeachersResponse.getResultCode()));
		assertTrue("Successfully completed".equals(actualTeachersResponse.getResultMessage()));
		assertEquals(teachers, actualTeachersResponse.getTeachers());
		verify(teachersRepository, times(1)).existsById(1);
		verify(teachersRepository, times(1)).save(teachers);

	}

	@Test
	void saveTeacherFailureTest() throws Exception {		
		when(teachersRepository.existsById(1)).thenReturn(true); 
		assertThrows(BusinessException.class, ()-> teacherServiceImpl.saveTeacher(new Teachers(1,"Balaji","Mechatronics")));
		verify(teachersRepository, times(1)).existsById(1);		
	}

}
