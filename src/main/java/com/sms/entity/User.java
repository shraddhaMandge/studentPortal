package com.sms.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false, updatable = false)
	private Long id;
	private String password;
	private String firstName;
	private String lastName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	private String phone;// long
	// private boolean enabled=true;

	public User() {

	}

	public User(long id, String password, String firstName, String lastName, String email, String phone, Role roles,
			Department department) {
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
		this.department = department;
	}

	// cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role roles;

	@ManyToOne
	@JoinColumn(name = "dept_id")
	private Department department;

	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Attendance> attendance;
	
	 //
	//@OneToMany(mappedBy = "user",orphanRemoval = true, cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private List<Marksheet> marksheet;

	public Long getId() {
		return id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	@JsonIgnore
	public List<Marksheet> getMarksheet() {
		return marksheet;
	}

	public void setMarksheet(List<Marksheet> marksheet) {
		this.marksheet = marksheet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
