package com.sms.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@Table(name="course")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="course_id")
	private int id;
	
	@Column(name="course_name",unique=true, nullable=false)
	private String courseName;
	
	
	@ManyToOne
	@JoinColumn(name="dept_id")
	@JsonBackReference(value="department")
	private Department department;
	
	
	
	@OneToMany(mappedBy="course", orphanRemoval = true, cascade = CascadeType.ALL)
 	private List<Attendance> attendace ;
		
	private String description;
	
	
	public Course() {
		
	}



	public Course(String courseName, Department department, String description) {
		this.courseName = courseName;
		this.department = department;
		this.description = description;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Course(String courseName) {
		this.courseName = courseName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + "]";
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


	@JsonIgnore
	public List<Attendance> getAttendace() {
		return attendace;
	}



	public void setAttendace(List<Attendance> attendace) {
		this.attendace = attendace;
	}
	
	
	
	
}



