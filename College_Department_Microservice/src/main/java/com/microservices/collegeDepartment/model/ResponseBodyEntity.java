package com.microservices.collegeDepartment.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.microservices.collegeDepartment.httpData.CollegeDepartmentResponse;

@JsonInclude(value = Include.NON_NULL)
public class ResponseBodyEntity {

	private String resultCode;
	
	private String resultMessage;
	
	private String studentServiceStatus;
	
	private CollegeDepartmentResponse collegeDepartmentResponse;
	
	private List<CollegeDepartmentResponse> collegeDepartmentResponses;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getStudentServiceStatus() {
		return studentServiceStatus;
	}

	public void setStudentServiceStatus(String studentServiceStatus) {
		this.studentServiceStatus = studentServiceStatus;
	}

	public CollegeDepartmentResponse getCollegeDepartmentResponse() {
		return collegeDepartmentResponse;
	}

	public void setCollegeDepartmentResponse(CollegeDepartmentResponse collegeDepartmentResponse) {
		this.collegeDepartmentResponse = collegeDepartmentResponse;
	}

	public List<CollegeDepartmentResponse> getCollegeDepartmentResponses() {
		return collegeDepartmentResponses;
	}

	public void setCollegeDepartmentResponses(List<CollegeDepartmentResponse> collegeDepartmentResponses) {
		this.collegeDepartmentResponses = collegeDepartmentResponses;
	}

	public ResponseBodyEntity() {
		super();
	}
	
}
