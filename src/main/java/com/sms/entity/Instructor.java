package com.sms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="instructor")// not using this class 
public class Instructor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="instructor_id",unique=true, nullable=false)
	private long id;

	@JsonIgnore
	@ManyToOne(targetEntity = User.class,cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;

	public Instructor() {
	}

	

	public Instructor(User user) {
		super();
		this.user = user;
	}



	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}











