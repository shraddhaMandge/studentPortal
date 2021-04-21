package com.sms.cntr;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sms.entity.User;
import com.sms.serv.HomeService;
import com.sms.serv.UserService;
import com.sms.utility.MailConstructor;

@CrossOrigin("*")
@RestController
public class HomeController {
	
	
	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private UserService userService;


	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;

	//@Autowired
	//private HomeService homeService;
	
	@PostMapping("/login")
	public User login(@RequestBody User user) {
		System.out.println("we reached here");
		User op = new User();
		String email = user.getEmail();
		String password = user.getPassword();
		Optional<User> lst = userService.findByEmailAndPassword(email, password);
		if(lst.isPresent()) {
			op = lst.get();
		}
		System.out.println(op.getFirstName()+" "+op.getEmail()+"  "+ op.getPassword());
		return op;// we neee to pass user here
	}
	
	/*
	@PostMapping("/forgetPassword/{email}")
	public String forgetPassword(@PathVariable String email) {

		User user = userService.findByEmail(email);
		
		if (user == null) {
			return "email not exist";
		}		
		String password =homeService.generatePassword(5);
		String encryptedPassword = passwordEncoder.encode(password);
		user.setPassword(encryptedPassword);
		
		userService.save(user);
	
		String msg="email not sent ";
		boolean status;
		try {
			status = mailConstructor.sendEmail("New Password","your new password is:"+encryptedPassword,email);
			if(status!=false)
				msg="send succesfully";
			
		} catch (Exception e) {
		
			msg="Bad Credentails";
			//e.printStackTrace();
		}
		return msg;
	}
	*/
}
