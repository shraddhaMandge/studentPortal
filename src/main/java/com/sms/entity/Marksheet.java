package com.sms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Marksheet {

	@Id
	@Column(name="marksheet_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int java;
	private int datastructure;
	private int angular;
	private int springboot;
	private int obtainmarks;
	private int totalmarks;
	private double percentage;
	
	@Column(nullable=false)
	private String department;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="user_id")
	private User user;
	

/*	@ManyToOne
	@JoinColumn(name="dept_id")
	private Department department;   // faced issue while refereces	
*/	
	
	public Marksheet()
	{
		
	}
	public Marksheet(int java, int datastructure, int angular, int springboot, int obtainmarks, int totalmarks,
			double percentage, String department, User user) {
		super();
		this.java = java;
		this.datastructure = datastructure;
		this.angular = angular;
		this.springboot = springboot;
		this.obtainmarks = obtainmarks;
		this.totalmarks = totalmarks;
		this.percentage = percentage;
		this.department = department;
		this.user = user;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getJava() {
		return java;
	}

	public void setJava(int java) {
		this.java = java;
	}


	public int getDatastructure() {
		return datastructure;
	}

	public void setDatastructure(int datastructure) {
		datastructure = datastructure;
	}


	public int getAngular() {
		return angular;
	}

	public void setAngular(int angular) {
		this.angular = angular;
	}

	public int getSpringboot() {
		return springboot;
	}


	public void setSpringboot(int springboot) {
		this.springboot = springboot;
	}


	public int getObtainmarks() {
		return obtainmarks;
	}


	public void setObtainmarks(int obtainmarks) {
		this.obtainmarks = obtainmarks;
	}

	public int getTotalmarks() {
		return totalmarks;
	}

	public void setTotalmarks(int totalmarks) {
		this.totalmarks = totalmarks;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}


	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
