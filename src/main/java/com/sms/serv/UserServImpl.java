package com.sms.serv;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Instructor;
import com.sms.entity.Role;
import com.sms.entity.Student;
import com.sms.entity.User;
import com.sms.repository.InstructorRepository;
import com.sms.repository.StudentRepository;
import com.sms.repository.UserRepository;

@Service
public class UserServImpl implements UserService {

	private UserRepository userRepo;

	
	@Autowired
	public UserServImpl(UserRepository userRepo) {

		this.userRepo = userRepo;
		System.out.println("repo wired");
	}
	
	
	@Override
	public User save(User user){
		
			return userRepo.save(user);
	}

	
	
	@Override
	public Iterable<User> findAll() {
		return userRepo.findAll();
	}

	
	
	@Override
	public User findByEmail(String email) {
		User result = userRepo.findByEmail(email);
		User theUser = null;

		if (result != null) {
			theUser = result;
		} else {
			 
			return  null;
			//throw new RuntimeException("Did not find emailId - " + email);
		}
		return theUser;
	}

	@Override
	public void deleteByEmail(String email) {
		userRepo.deleteByEmail(email);
	}
	
	
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
	}


	@Override
	public Iterable<User> findByDeptAndRole(int dept_id, int role_id) {
		
		return userRepo.findByDeptAndRole(dept_id, role_id);
	}


	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		return userRepo.findByEmailAndPassword(email, password);
	}

}
