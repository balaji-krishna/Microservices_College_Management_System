package com.microservices.collegeDepartment.model;

public class Student {

	private int id;
	
	private String name;
	
	private String email;
	
	private int departmentId;

	public Student(int id, String name, String email, int departmentId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.departmentId = departmentId;
	}

	public Student() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", departmentId=" + departmentId + "]";
	}
	
}
