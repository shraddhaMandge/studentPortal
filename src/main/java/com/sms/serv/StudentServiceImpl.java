package com.sms.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Student;
import com.sms.repository.StudentRepository;

@Service
public class StudentServiceImpl  implements StudentService{

	private StudentRepository studentRepo;
	
	@Autowired
	public void setStudentRepo(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}

	@Override
	public List<Student> findAll() {
		return studentRepo.findAll();
	}

	@Override
	public List<Student> findAllByDepartment(int id) {
	
			return studentRepo.findAllByDepartment(id);

	}

	@Override
	public Student findByEmail(String email) {
		
		Student result = studentRepo.findByEmail(email);
		Student theStudent = null;
		
		if (result!=null) {
			theStudent = result;
		}
		else {
			 //we didn't find the student
			throw new RuntimeException("Did not find  emailId :" + email);
		}
		return theStudent;
	}
	
}
