package com.sms.utility;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;


@Service
public class MailConstructor {

	
	
	public boolean sendEmail(String subject, String message, String to) throws Exception{
		
		String from="cdacproj465@gmail.com";
		
		
		//Variable for gmail
		String host="smtp.gmail.com";
		
		boolean result=false;
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");

		System.out.println("post accepted");
		
		
		
		//Step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("cdacproj465@gmail.com","Cdac@123");
			}
		});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
		
		//from email
		m.setFrom(new InternetAddress(from));
		//adding recipient to message
		m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
	
		
		//adding text to message
		m.setText(message);
		
		//send 
		
		//Step 3 : send the message using Transport class
	
		System.out.println("till this no problem");
		Transport.send(m);
		
		System.out.println("Sent success...................");
		
		result=true;
		}catch (Exception e) {
			e.printStackTrace();
				throw new RuntimeException();
		}
		
		return result;
			
	}
	
}
