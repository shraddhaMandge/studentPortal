package com.sms.entity;

import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Assignment {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	 
	private String name;
	
	private String description;
	
	private String type;
	
	
	private String department;
	
	
	private String courseName;
	
	@Lob
	private byte[] data;
	
	public Assignment() {
		
	}
	

	public Assignment(String name, String description, String type, String  department, String courseName,byte []data) {
		
		this.name = name;
		this.description = description;
		this.type = type;
		this.department = department;
		this.courseName = courseName;
		this.data = data;
	}


	public Assignment(String name, String contentType, byte[] data) {
		
		this.name = name;
		this.type = contentType;
		this.data = data;
		
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	/*public Stream<Assignment> map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
