package com.sms.serv;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Course;
import com.sms.entity.User;
import com.sms.repository.CourseRepository;

@Service
public class CourseServImpl implements CourseService {

	private CourseRepository courseRepo;
	

	@Autowired
	public void setCourseRepo(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}

	@Override
	public Iterable<Course> findAll() {
		return courseRepo.findAll();
	}

	@Override
	public Course findByCourseName(String name) {
		// TODO Auto-generated method stub
		return courseRepo.findByCourseName(name);
	}

	@Override
	public Course save(Course course) {
		// TODO Auto-generated method stub
		return courseRepo.save(course);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		courseRepo.deleteById(id);
	}

	@Override
	public Course findById(int theId) {
		Optional<Course> result = courseRepo.findById(theId);
		
		Course theCourse = null;
		
		if (result.isPresent()) {
			theCourse = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find Course id - " + theId);
		}
		
		return theCourse;
	}
}
