package com.sms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Notification {

	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	 
	private String name;
	
	private String description;
	
	private String type;
	
	
	private String department;
	
	

	
	@Lob
	private byte[] data;
	
	public Notification() {
		
	}
	

	public Notification(String name, String description, String type, String  department,byte []data) {
		
		this.name = name;
		this.description = description;
		this.type = type;
		this.department = department;
		this.data = data;
	}


	public Notification(String name, String contentType, byte[] data) {
		
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
}
