package com.microservices.collegeDepartment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Teachers {
	
	@Id
	private int teacherId;
	
	@Column(name = "teacher_name")
	private String teacherName;
	
	@Column(name = "teacher_department")
	private String teacherDepartment;

	public Teachers() {
		super();
	}

	public Teachers(int teacherId, String teacherName, String teacherDepartment) {
		super();
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.teacherDepartment = teacherDepartment;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherDepartment() {
		return teacherDepartment;
	}

	public void setTeacherDepartment(String teacherDepartment) {
		this.teacherDepartment = teacherDepartment;
	}

	@Override
	public String toString() {
		return "Teachers [teacherId=" + teacherId + ", teacherName=" + teacherName + ", teacherDepartment="
				+ teacherDepartment + "]";
	}

}
