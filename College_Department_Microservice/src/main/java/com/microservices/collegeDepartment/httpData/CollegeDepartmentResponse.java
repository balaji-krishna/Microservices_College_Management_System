package com.microservices.collegeDepartment.httpData;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.microservices.collegeDepartment.entity.CollegeDepartment;
import com.microservices.collegeDepartment.model.Student;

@JsonInclude(value = Include.NON_NULL)
public class CollegeDepartmentResponse {
	
	private CollegeDepartment collegeDepartment;

	private List<Student> students;
	
	private List<CollegeDepartment> collegeDepartments;

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

	public List<CollegeDepartment> getCollegeDepartments() {
		return collegeDepartments;
	}

	public void setCollegeDepartments(List<CollegeDepartment> collegeDepartments) {
		this.collegeDepartments = collegeDepartments;
	}

	public CollegeDepartmentResponse(CollegeDepartment collegeDepartment, List<Student> students,
			List<CollegeDepartment> collegeDepartments) {
		super();
		this.collegeDepartment = collegeDepartment;
		this.students = students;
		this.collegeDepartments = collegeDepartments;
	}

	public CollegeDepartmentResponse() {
		super();
	}

	@Override
	public String toString() {
		return "CollegeDepartmentResponse [collegeDepartment=" + collegeDepartment + ", students=" + students
				+ ", collegeDepartments=" + collegeDepartments + "" + "]";
	}

}
