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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.microservices.collegeDepartment.entity.CollegeDepartment;
import com.microservices.collegeDepartment.exception.BusinessException;
import com.microservices.collegeDepartment.model.ResponseBodyEntity;
import com.microservices.collegeDepartment.repository.CollegeDepartmentRepository;

@ExtendWith(MockitoExtension.class)
public class CollegeDepartmentServiceImplTest {

	@InjectMocks
	CollegeDepartmentServiceImpl collegeDepartmentServiceImpl;

	@Mock
	CollegeDepartmentRepository collegeDepartmentRepository;

	@Mock
	RestTemplate restTemplate;

	@Test
	void saveDepartmentSuccessTest() throws Exception {

		CollegeDepartment collegeDepartment = new CollegeDepartment();
		collegeDepartment.setDepartmentId(1);
		collegeDepartment.setDepartmentName("Mechatronics");

		when(collegeDepartmentRepository.existsById(1)).thenReturn(false);
		when(collegeDepartmentRepository.save(collegeDepartment)).thenReturn(collegeDepartment);

		ResponseBodyEntity responseBodyEntity = collegeDepartmentServiceImpl.saveDepartment(collegeDepartment);

		assertTrue("01".equals(responseBodyEntity.getResultCode()));
		assertTrue("Successfully completed".equals(responseBodyEntity.getResultMessage()));
		assertEquals(collegeDepartment, responseBodyEntity.getCollegeDepartmentResponse().getCollegeDepartment());
		verify(collegeDepartmentRepository, times(1)).existsById(1);
		verify(collegeDepartmentRepository, times(1)).save(collegeDepartment);

	}

	@Test
	void saveDepartmentFailureTest() throws Exception {		
		when(collegeDepartmentRepository.existsById(1)).thenReturn(true); 
		assertThrows(BusinessException.class, ()-> collegeDepartmentServiceImpl.saveDepartment(new CollegeDepartment(1,"Mechatronics")));
		verify(collegeDepartmentRepository, times(1)).existsById(1);		
	}

	@Test
	void updateDepartmentSuccessTest() throws Exception {

		CollegeDepartment collegeDepartment = new CollegeDepartment();
		collegeDepartment.setDepartmentId(1);
		collegeDepartment.setDepartmentName("Mechatronics");

		when(collegeDepartmentRepository.existsById(1)).thenReturn(true);
		when(collegeDepartmentRepository.save(collegeDepartment)).thenReturn(collegeDepartment);

		ResponseBodyEntity responseBodyEntity = collegeDepartmentServiceImpl.updateDepartment(collegeDepartment);

		assertTrue("01".equals(responseBodyEntity.getResultCode()));
		assertTrue("Successfully completed".equals(responseBodyEntity.getResultMessage()));
		assertEquals(collegeDepartment, responseBodyEntity.getCollegeDepartmentResponse().getCollegeDepartment());
		verify(collegeDepartmentRepository, times(1)).existsById(1);
		verify(collegeDepartmentRepository, times(1)).save(collegeDepartment);

	}

	@Test
	void updateDepartmentFailureTest() throws Exception {		
		when(collegeDepartmentRepository.existsById(1)).thenReturn(false); 
		assertThrows(BusinessException.class, ()-> collegeDepartmentServiceImpl.updateDepartment(new CollegeDepartment(1,"Mechatronics")));
		verify(collegeDepartmentRepository, times(1)).existsById(1);		
	}

	@Test
	void deleteDepartmentByIdSuccessTest() throws Exception {

		CollegeDepartment collegeDepartment = new CollegeDepartment();
		collegeDepartment.setDepartmentId(1);
		collegeDepartment.setDepartmentName("Mechatronics");

		CollegeDepartment collegeDepartment2 = new CollegeDepartment();
		collegeDepartment2.setDepartmentId(2);
		collegeDepartment2.setDepartmentName("ECE");

		List<CollegeDepartment> collegeDepartments = new ArrayList<>();
		collegeDepartments.add(collegeDepartment);
		collegeDepartments.add(collegeDepartment2);

		when(collegeDepartmentRepository.existsById(1)).thenReturn(true);
		when(collegeDepartmentRepository.findAll()).thenReturn(collegeDepartments);

		ResponseBodyEntity responseBodyEntity = collegeDepartmentServiceImpl.deleteDepartmentById(1);

		assertTrue("01".equals(responseBodyEntity.getResultCode()));
		assertTrue("Successfully completed".equals(responseBodyEntity.getResultMessage()));
		verify(collegeDepartmentRepository, times(1)).existsById(1);
		verify(collegeDepartmentRepository, times(1)).findAll();

	}

	@Test
	void deleteDepartmentByIdFailureTest() throws Exception {
		when(collegeDepartmentRepository.existsById(1)).thenReturn(false);
		assertThrows(BusinessException.class, ()-> collegeDepartmentServiceImpl.deleteDepartmentById(1));
		verify(collegeDepartmentRepository, times(1)).existsById(1);
	}

	@Test
	void getAllDepartmentsInfoFallbackSuccessTest() throws Exception {

		CollegeDepartment collegeDepartment = new CollegeDepartment();
		collegeDepartment.setDepartmentId(1);
		collegeDepartment.setDepartmentName("Mechatronics");

		CollegeDepartment collegeDepartment2 = new CollegeDepartment();
		collegeDepartment2.setDepartmentId(2);
		collegeDepartment2.setDepartmentName("ECE");

		List<CollegeDepartment> collegeDepartments = new ArrayList<>();
		collegeDepartments.add(collegeDepartment);
		collegeDepartments.add(collegeDepartment2);
		when(collegeDepartmentRepository.findAll()).thenReturn(collegeDepartments);

		ResponseBodyEntity responseBodyEntity = collegeDepartmentServiceImpl.getAllDepartmentsInfoFallback("1",
				new Exception());

		assertTrue("01".equals(responseBodyEntity.getResultCode()));
		assertTrue("Successfully completed".equals(responseBodyEntity.getResultMessage()));
		assertTrue("DOWN".equals(responseBodyEntity.getStudentServiceStatus()));
		verify(collegeDepartmentRepository, times(1)).findAll();

	}

	@Test
	void getDepartmentByIdFallbackSuccessTest() throws Exception {

		CollegeDepartment collegeDepartment = new CollegeDepartment();
		collegeDepartment.setDepartmentId(1);
		collegeDepartment.setDepartmentName("Mechatronics");

		Optional<CollegeDepartment> optional = Optional.ofNullable(collegeDepartment);

		when(collegeDepartmentRepository.findById(1)).thenReturn(optional);

		ResponseBodyEntity responseBodyEntity = collegeDepartmentServiceImpl.getDepartmentByIdFallback(1, "1",
				new RestClientException("1"));

		assertTrue("01".equals(responseBodyEntity.getResultCode()));
		assertTrue("Successfully completed".equals(responseBodyEntity.getResultMessage()));
		assertTrue("DOWN".equals(responseBodyEntity.getStudentServiceStatus()));
		verify(collegeDepartmentRepository, times(1)).findById(1);

	}

}
