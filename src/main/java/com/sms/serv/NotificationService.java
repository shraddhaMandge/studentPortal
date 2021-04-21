package com.sms.serv;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.sms.entity.Assignment;
import com.sms.entity.Notification;

public interface NotificationService {

	
	public Stream<Notification> getAllFiles(String department);
	
	public Stream<Notification> getAllFiles();
	
	public Notification store(MultipartFile file ) throws IOException;

	public Notification addNotification(Notification  notification);

	public void deleteFile(String id);
	
	public Optional<Notification> getFile(String fileId); 
	
	
	
}
