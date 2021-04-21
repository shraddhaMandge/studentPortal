package com.sms.serv;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sms.entity.Assignment;
import com.sms.entity.Notification;
import com.sms.repository.NotificationRepository;

@Service
public class NotificationServImpl implements NotificationService {

	private NotificationRepository notificationRepo;
	
	
	@Autowired
	public void setNotificationRepo(NotificationRepository notificationRepo) {
		this.notificationRepo = notificationRepo;
	}

	@Override
	  public Notification store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    Notification FileDB = new Notification(fileName, file.getContentType(), file.getBytes());

	    return notificationRepo.save(FileDB);
	  }

	  @Override
	  public Stream<Notification> getAllFiles(String department) {
	    return notificationRepo.findAllByDepartment(department).stream();//.get();
	  }
	  
	  @Override
	  public Stream<Notification> getAllFiles() {
	    return notificationRepo.findAll().stream();
	  }

	@Override
	public Notification addNotification(Notification notification) {
		return notificationRepo.save(notification);	
	}
	@Override
	public void deleteFile(String id) {
		
		notificationRepo.deleteById(id);		
	}

	@Override
	public Optional<Notification> getFile(String fileId) {
		return notificationRepo.findById(fileId);
	}

}
