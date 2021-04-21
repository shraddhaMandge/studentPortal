package com.sms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class StudentAssignment {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	 
	private String name;
	
	private String description;
	
	private String studentName ;  
	
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	private String department;
	
	
	private String courseName;
	
	

	public StudentAssignment(String id, String name, String description, String studentName, String type,
			String department, String courseName, byte[] data) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.studentName = studentName;
		this.type = type;
		this.department = department;
		this.courseName = courseName;
		this.data = data;
	}

	@Lob
	private byte[] data;
	
	public StudentAssignment() {
		// TODO Auto-generated constructor stub
	}

}
