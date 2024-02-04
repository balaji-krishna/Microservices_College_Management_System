package com.microservices.collegeDepartment.httpData;

import java.util.List;

import com.microservices.collegeDepartment.model.Student;

public class StudentResponseBody {
	
	private String resultCode;
	
	private String resultMessage;
	
	private Student student;
	
	private List<Student> students;

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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public StudentResponseBody() {
		super();
	}

	public StudentResponseBody(String resultCode, String resultMessage, Student student, List<Student> students) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.student = student;
		this.students = students;
	}

	@Override
	public String toString() {
		return "StudentResponseBody [resultCode=" + resultCode + ", resultMessage=" + resultMessage + ", student="
				+ student + ", students=" + students + "]";
	}

}
