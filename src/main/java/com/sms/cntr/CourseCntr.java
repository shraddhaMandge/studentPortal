package com.sms.cntr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sms.entity.Course;
import com.sms.serv.CourseService;


@RestController
public class CourseCntr {

	
	private   CourseService courseService ;
	
	
	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}


	@GetMapping("/courses")
	public Iterable<Course> findAll()
	{
		return courseService.findAll();
	}
	
	@GetMapping("/courses/{courseName}")
	public Course getCourse(@PathVariable String courseName)
	{
		Course course=courseService.findByCourseName(courseName);
		if(course==null)
			throw new RuntimeException("Course Does not Exist");
		
		return course;
	}
	
	@PostMapping("/courses")
	public String addCourse(@RequestBody Course course)
	{
		
		course.setId(0);//otherwise it might call update method
		String str="course not added";
		try {
			if(courseService.save(course)!=null)
				str="Course added succcessfully";
			
		}
		catch(Exception e)
		{
			str="Bad credentails";
			return str;
		}
		return str;
	}
	
	@PutMapping("/courses")
	public String updateCourse(@RequestBody Course course)
	{
		String str="update failed";
		try {
			if(courseService.save(course)!=null)
			{
				str="Course updated succesfully";
			}
		}
		catch(Exception e)
		{
			str="Bad Crendentails";
			return str;
		}
		return str;
	}
	
	@DeleteMapping("/courses/{id}")
	public String deleteCourse(@PathVariable int id) {
		Course course = courseService.findById(id);
		if (course == null) {
			throw new RuntimeException(" course not exist " );
		}
		courseService.deleteById(id);;
		return "Deleted Course";
	}	
	
}
