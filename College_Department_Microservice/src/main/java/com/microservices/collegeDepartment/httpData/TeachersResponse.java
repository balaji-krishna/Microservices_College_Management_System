package com.microservices.collegeDepartment.httpData;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.microservices.collegeDepartment.entity.Teachers;

@JsonInclude(value = Include.NON_NULL)
public class TeachersResponse {
	
	private String resultCode;
	
	private String resultMessage;
	
	private Teachers teachers;
	
	private List<Teachers> teachersList;
	
	private String profileName;

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

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	@Override
	public String toString() {
		return "TeachersResponse [resultCode=" + resultCode + ", resultMessage=" + resultMessage + ", teachers="
				+ teachers + ", teachersList=" + teachersList + ", profileName=" + profileName + "]";
	}

}
