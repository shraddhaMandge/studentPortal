package com.sms.serv;


import com.sms.entity.Course;
import com.sms.entity.User;

public interface CourseService {
	
	public Iterable<Course> findAll();
	
	public Course findByCourseName(String name);
	
	public Course save(Course course);
	
	public void deleteById(int id);

	public Course findById(int id);

}
