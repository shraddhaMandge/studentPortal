package com.sms.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Instructor;
import com.sms.repository.InstructorRepository;


@Service
public class InstructorServImpl implements InstructorService{

	private InstructorRepository instructorRepo;
	
	
	@Autowired
	public void setInstructorRepo(InstructorRepository instructorRepo) {
		this.instructorRepo = instructorRepo;
	}

	@Override
	public List<Instructor> findAll() {
		
		return instructorRepo.findAll();
	}

	@Override
	public Instructor findByEmail(String email) {
		
		return instructorRepo.findByEmail(email);
	}

}
