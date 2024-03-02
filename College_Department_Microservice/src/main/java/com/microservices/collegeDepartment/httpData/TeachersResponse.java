package com.microservices.collegeDepartment.httpData;

import java.util.List;

import com.microservices.collegeDepartment.entity.Teachers;

public class TeachersResponse {
	
private String resultCode;
	
	private String resultMessage;
	
	private Teachers teachers;
	
	private List<Teachers> teachersList;

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

	public Teachers getTeachers() {
		return teachers;
	}

	public void setTeachers(Teachers teachers) {
		this.teachers = teachers;
	}

	public List<Teachers> getTeachersList() {
		return teachersList;
	}

	public void setTeachersList(List<Teachers> teachersList) {
		this.teachersList = teachersList;
	}

	@Override
	public String toString() {
		return "TeachersResponse [resultCode=" + resultCode + ", resultMessage=" + resultMessage + ", teachers="
				+ teachers + ", teachersList=" + teachersList + "]";
	}

}
