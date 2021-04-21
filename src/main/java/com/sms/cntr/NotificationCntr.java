package com.sms.cntr;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.entity.Assignment;
import com.sms.entity.Notification;
import com.sms.entity.ResponseFile;
import com.sms.entity.ResponseMessage;
import com.sms.serv.NotificationService;

@CrossOrigin("*")
@RestController
public class NotificationCntr {

	
	private NotificationService notificationServ;
	
	@Autowired
	public void setNotificationServ(NotificationService notificationServ) {
		this.notificationServ = notificationServ;
	}

	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/uploadNotification")
	public ResponseEntity<ResponseMessage> uploadFile(
			@RequestParam(value = "File", required = false) MultipartFile file,
			@RequestParam(required = true, value = "asgJson") String asgJson) {
		String message = "";
		try {
			//notificationServ.store(file);

			Notification notification = objectMapper.readValue(asgJson, Notification.class);
			notification.setData(file.getBytes());
			 notification.setName(file.getOriginalFilename());
			 notification.setType(file.getContentType());
			
			notificationServ.addNotification(notification);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
//			e.printStackTrace();
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/notification")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = notificationServ.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/notify/")
					.path(dbFile.getId()).toUriString();

			return new ResponseFile(dbFile.getId(),dbFile.getName(), fileDownloadUri, dbFile.getType(),
					// dbFile.getData().length,
					dbFile.getDepartment(), dbFile.getDescription());
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/notification/{department}")
	public ResponseEntity<List<ResponseFile>> getFiles(@PathVariable String department) {
		List<ResponseFile> files = notificationServ.getAllFiles(department).map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/notify/")
					.path(dbFile.getId()).toUriString();

			return new ResponseFile(dbFile.getId(),dbFile.getName(), fileDownloadUri, dbFile.getType(),
					//dbFile.getData().length,
					dbFile.getDepartment(), dbFile.getDescription());
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	@DeleteMapping("/notification/{id}")
	public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String id){
	
		String message = "";
		
		try {
			notificationServ.deleteFile(id);
			message = "Deleted the file successfully " ;
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
//			e.printStackTrace();
			message = "Could not Delete the file";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
		
	}
	
	@GetMapping("/notify/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId){
		Notification doc = notificationServ.getFile(fileId).get();
		return ResponseEntity.ok()
				//.contentType(MediaType.parseMediaType("png/pdf"))
				//.contentType(MediaType.parseMediaType("application/text"))
				.contentType(MediaType.parseMediaType(doc.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
	
	
	
}
