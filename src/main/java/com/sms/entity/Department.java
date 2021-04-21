package com.sms.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


	@Entity
	@Table(name = "department")
	public class Department 
	{
		@Id
		@Column(name="dept_id",unique=true, nullable=false)
		private int id;

		@Column(name="department_name",length=255)
		private String name;

		//bi-directional many-to-one association to Course
		
		@OneToMany(mappedBy="department")
		private Set<Course> courses;

		//bi-directional many-to-one association to user
		
		@OneToMany(mappedBy="department")
		private Set<User> user;

		//@OneToMany(mappedBy="department")
		//private Set<Marksheet> marksheet;
		
		//@OneToMany(mappedBy="department", orphanRemoval = true, cascade = CascadeType.ALL)
		@OneToMany(mappedBy="department")
	 	private List<Attendance> attendance ;
		
		
		public Department(String name, Set<Course> courses, Set<User> user) {
			this.name = name;
			this.courses = courses;
			this.user = user;
		}

		@JsonIgnore
		public Set<User> getUser() {
			return user;
		}

		public void setUser(Set<User> user) {
			this.user = user;
		}

		public Department() {
		}

		public int getId() {
			return this.id;
		}

		public void setId(int id) {
			this.id = id;
		}

		
		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@JsonIgnore
		public Set<Course> getCourses() {
			return this.courses;
		}

		public void setCourses(Set<Course> courses) {
			this.courses = courses;
		}

		@JsonIgnore
		public List<Attendance> getAttendance() {
			return attendance;
		}

		public void setAttendance(List<Attendance> attendace) {
			this.attendance = attendace;
		}

		public Course addCours(Course cours) {
			getCourses().add(cours);
			cours.setDepartment(this);

			return cours;
		}

		public Course removeCours(Course cours) {
			getCourses().remove(cours);
			cours.setDepartment(null);

			return cours;
		}
	}
