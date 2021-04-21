package com.sms.cntr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sms.entity.Student;
import com.sms.serv.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class StudentCntr {
	
	private StudentService studentServ;

	@Autowired
	public void setStudentServ(StudentService studentServ) {
		this.studentServ = studentServ;
	}
	
	@GetMapping("/student")
	public List<Student> showStudents()
	{
		List<Student> student=studentServ.findAll();
		if(student.isEmpty())
			return null;// if front end need to cheak query as if null then list is empty
		else
			return student;//this condt is due to if list is empty then it return empty array
		// dont throw runtime exception
	}
	
	
	@GetMapping("/student/{id}")
	public List<Student> getStudentByDepartment(@PathVariable int id)
	{
		return studentServ.findAllByDepartment(id);
	}
	
	@GetMapping
	public Student findStudentByEmail(@PathVariable String email)
	{
		return studentServ.findByEmail(email);
	}
	

}
