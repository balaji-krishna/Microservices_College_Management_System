package com.microservices.collegeDepartment.model;

import java.util.List;

import com.microservices.collegeDepartment.entity.CollegeDepartment;

public class ResponseBodyEntity {
	
	private CollegeDepartment collegeDepartment;
	
	private List<Student> students;

	public ResponseBodyEntity() {
		super();
	}

	public ResponseBodyEntity(CollegeDepartment collegeDepartment, List<Student> students) {
		super();
		this.collegeDepartment = collegeDepartment;
		this.students = students;
	}

	public CollegeDepartment getCollegeDepartment() {
		return collegeDepartment;
	}

	public void setCollegeDepartment(CollegeDepartment collegeDepartment) {
		this.collegeDepartment = collegeDepartment;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "ResponseBodyEntity [collegeDepartment=" + collegeDepartment + ", students=" + students + "]";
	}

}
