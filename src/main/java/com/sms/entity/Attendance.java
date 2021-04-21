package com.sms.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Attendance {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="attendace_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="dept_id")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	private String remark;
	
	//@JsonFormat(pattern="dd/MM/yy")
	private Date date;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

	public Attendance() {}
	

	public Attendance(Department department, Course course, String remark, Date date, User user) {
		super();
		this.department = department;
		this.course = course;
		this.remark = remark;
		this.date = date;
		this.user = user;
	}


	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
