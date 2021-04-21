package com.sms.cntr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sms.entity.Status;
import com.sms.entity.User;
import com.sms.serv.UserServImpl;
import com.sms.utility.MailConstructor;

@CrossOrigin("*")
@RestController
public class UserCntr {

	private UserServImpl userService;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	public UserCntr(UserServImpl userService) {
		
		this.userService = userService;
		System.out.println("service autowired");
	}


	@GetMapping("/users")
	public Iterable<User> findAll() {
		return userService.findAll();
	}
	
	//get userlist based on userdId and roleID =1
	@PostMapping("/userList")
	public Iterable<User> findAllByDeptAndRole(@RequestBody User user) {
		System.out.println("we reached here");
		System.out.println(user.getDepartment().getId()+"   "+user.getRoles().getRoleId());
		int dept_id = user.getDepartment().getId();
		int role_id = user.getRoles().getRoleId();
		
		return userService.findByDeptAndRole(dept_id, role_id);
	}

	
	@GetMapping("/users/{email}")
	public User getUser(@PathVariable String email) {
		
		User theUser = userService.findByEmail(email);
		
		if (theUser == null) {
			throw new RuntimeException("User id not found - " + email);
		}
		
		return theUser;
	}
	
	
	@PostMapping("/users")
	public Status addUser(@RequestBody User user) {
		Status stsStatus = new Status("error" , false);
		//testing 
		System.out.println(user.getEmail()+" "+user.getRoles().getRoleId()+" "+user.getDepartment().getId());
	
		//user.setId((long) 0);
		if (userService.findByEmail(user.getEmail()) != null) {
			
			stsStatus.setMsg("email already exist ");
		}
		
	//	String password = SecurityUtility.randomPassword();
		//String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		//user.setPassword(encryptedPassword);
				
		try {
			
		boolean status=mailConstructor.sendEmail("Cdac user and Password","your user id is : "+user.getEmail()+" and password is : "+user.getPassword(),user.getEmail());	
		
		userService.save(user);
		if(status!=false)
		stsStatus.setMsg("user registered ");
			
		}
		catch(RuntimeException e)
		{
		stsStatus.setMsg("Bad Credentials");
		}
		catch(Exception e)
		{
			stsStatus.setMsg("somthing went wrong ");		
		}
		System.out.println();
		System.out.println(stsStatus.getMsg());
		return stsStatus;// for timebeing
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		
		
		//String str="update failed";
		try {
			System.out.println("we reached here");
			System.out.println(user.getEmail()+" "+user.getId());
			userService.save(user);
			System.out.println(user.getEmail()+" "+user.getId());
		}
		catch(Exception e)
		{
			System.out.println("we got a exception");
			e.printStackTrace();
			System.out.println(e);
			user.setEmail("");
		}
		return user;
		
	}
	
	@DeleteMapping("/users/{id}")
	public Status deleteUser(@PathVariable long id) {
		Status stsStatus = new Status("Deleted User", true);
	
		userService.deleteById(id);
		
		return stsStatus;
	}	
	

	

}
