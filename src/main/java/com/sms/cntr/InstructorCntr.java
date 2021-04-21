package com.sms.cntr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sms.entity.Instructor;
import com.sms.serv.InstructorService;

@RestController
public class InstructorCntr {

	private InstructorService instructorServ;

	@Autowired
	public void setInstructorServ(InstructorService instructorServ) {
		this.instructorServ = instructorServ;
	}

	@GetMapping("/instructor")
	public List<Instructor> showInstructors()
	{
		List<Instructor> instructor=instructorServ.findAll();
		if(instructor.isEmpty())
			return null;        // if front end need to cheak query as if null then list is empty
		else
			return instructor;
		//this condt is due to if list is empty then it return empty array
		// dont throw runtime exception
	}
	
	@GetMapping("/instructor/{email}")
	public Instructor findInstructorByEmail(@PathVariable String email)
	{
		return instructorServ.findByEmail(email);
	}
	

	
	
}
