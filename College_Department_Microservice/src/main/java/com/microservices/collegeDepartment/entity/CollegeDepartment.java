package com.microservices.collegeDepartment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CollegeDepartment {
	
	@Id
	@Column(name = "department_Id")
	private int departmentId;
	
	@Column(name = "department_Name")
	private String departmentName;

	public CollegeDepartment() {
		super();
	}

	public CollegeDepartment(int departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "CollegeDepartment [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}	

}
