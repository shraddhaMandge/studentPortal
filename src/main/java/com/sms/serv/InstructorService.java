package com.sms.serv;

import java.util.List;

import com.sms.entity.Instructor;


public interface InstructorService {
	
	
	public List<Instructor> findAll();
	
	public Instructor findByEmail(String email);
	

}
