package com.sms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sms.entity.Course;
import com.sms.entity.User;


@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

	
	
	Course findByCourseName(String name);

	
	
	
}
