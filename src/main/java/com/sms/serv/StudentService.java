package com.sms.serv;

import java.util.List;

import com.sms.entity.Student;


public interface StudentService {
	
	public List<Student> findAll();
	
	public List<Student> findAllByDepartment(int  deptId);
	
	public Student findByEmail(String email);
	
	

}
