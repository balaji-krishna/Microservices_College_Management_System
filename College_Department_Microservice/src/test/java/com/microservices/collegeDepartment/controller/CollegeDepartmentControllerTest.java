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

import com.microservices.collegeDepartment.entity.CollegeDepartment;
import com.microservices.collegeDepartment.model.ResponseBodyEntity;
import com.microservices.collegeDepartment.service.CollegeDepartmentService;

@ExtendWith(MockitoExtension.class)
public class CollegeDepartmentControllerTest {

	@InjectMocks
	CollegeDepartmentController collegeDepartmentController;

	@Mock
	CollegeDepartmentService collegeDepartmentService;

	@Test
	void getAllDepartmentsInfoSuccessTest() throws Exception {

		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");

		when(collegeDepartmentService.getAllDepartmentsInfo("1")).thenReturn(responseBodyEntity);

		ResponseBodyEntity actualResponseBodyEntity = collegeDepartmentController.getAllDepartmentsInfo("1");

		assertTrue("01".equals(actualResponseBodyEntity.getResultCode()));
		assertTrue("Successfully completed".equals(actualResponseBodyEntity.getResultMessage()));
		verify(collegeDepartmentService, times(1)).getAllDepartmentsInfo("1");

	}

	@Test
	void getDepartmentByIdSuccessTest() throws Exception {

		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");

		when(collegeDepartmentService.getDepartmentById(1, "1")).thenReturn(responseBodyEntity);

		ResponseBodyEntity actualResponseBodyEntity = collegeDepartmentController.getDepartmentById(1, "1");

		assertTrue("01".equals(actualResponseBodyEntity.getResultCode()));
		assertTrue("Successfully completed".equals(actualResponseBodyEntity.getResultMessage()));
		verify(collegeDepartmentService, times(1)).getDepartmentById(1, "1");

	}
	
	@Test
	void saveDepartmentTest() throws Exception {
		
		CollegeDepartment collegeDepartment = new CollegeDepartment();
		collegeDepartment.setDepartmentId(1);
		collegeDepartment.setDepartmentName("Mechatronics");

		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");

		when(collegeDepartmentService.saveDepartment(collegeDepartment)).thenReturn(responseBodyEntity);

		ResponseBodyEntity actualResponseBodyEntity = collegeDepartmentController.saveDepartment(collegeDepartment, "1");

		assertTrue("01".equals(actualResponseBodyEntity.getResultCode()));
		assertTrue("Successfully completed".equals(actualResponseBodyEntity.getResultMessage()));
		verify(collegeDepartmentService, times(1)).saveDepartment(collegeDepartment);

	}
	
	@Test
	void updateDepartmentSuccessTest() throws Exception {
		
		CollegeDepartment collegeDepartment = new CollegeDepartment();
		collegeDepartment.setDepartmentId(1);
		collegeDepartment.setDepartmentName("Mechatronics");

		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");

		when(collegeDepartmentService.updateDepartment(collegeDepartment)).thenReturn(responseBodyEntity);

		ResponseBodyEntity actualResponseBodyEntity = collegeDepartmentController.updateDepartment(collegeDepartment, "1");

		assertTrue("01".equals(actualResponseBodyEntity.getResultCode()));
		assertTrue("Successfully completed".equals(actualResponseBodyEntity.getResultMessage()));
		verify(collegeDepartmentService, times(1)).updateDepartment(collegeDepartment);

	}

	@Test
	void deleteDepartmentByIdSuccessTest() throws Exception {

		ResponseBodyEntity responseBodyEntity = new ResponseBodyEntity();
		responseBodyEntity.setResultCode("01");
		responseBodyEntity.setResultMessage("Successfully completed");

		when(collegeDepartmentService.deleteDepartmentById(1)).thenReturn(responseBodyEntity);

		ResponseBodyEntity actualResponseBodyEntity = collegeDepartmentController.deleteDepartmentById(1, "1");

		assertTrue("01".equals(actualResponseBodyEntity.getResultCode()));
		assertTrue("Successfully completed".equals(actualResponseBodyEntity.getResultMessage()));
		verify(collegeDepartmentService, times(1)).deleteDepartmentById(1);

	}

}
