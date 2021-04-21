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
import com.sms.entity.ResponseFile;
import com.sms.entity.ResponseMessage;
import com.sms.entity.StudentAssignment;
import com.sms.serv.AssignmentService;
import com.sms.serv.AssignmentSubmittedService;

@RestController
@CrossOrigin("*")
public class AssignmentSumbittedCntr {
	
	private AssignmentSubmittedService asgService;

	@Autowired
	public void setAsgService(AssignmentSubmittedService asgService) {
		this.asgService = asgService;
	}
	
	
	
	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/submit")
	public ResponseEntity<ResponseMessage> uploadFile(
			@RequestParam(value = "File", required = false) MultipartFile file,
			@RequestParam(required = true, value = "asgJson") String asgJson) {
		String message = "";
		try {
			//asgService.store(file);

			StudentAssignment assignment = objectMapper.readValue(asgJson, StudentAssignment.class);
			 assignment.setData(file.getBytes());
			 assignment.setName(file.getOriginalFilename());
			 assignment.setType(file.getContentType());

			asgService.addAssignment(assignment);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
//			e.printStackTrace();
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
	
	
	
	
	@GetMapping("/submittedAssign")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = asgService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/submittedAssign/")
					.path(dbFile.getId()).toUriString();

			return new ResponseFile(dbFile.getId() , dbFile.getName(), fileDownloadUri, dbFile.getType(),
					// dbFile.getData().length,
					dbFile.getDepartment(), dbFile.getCourseName(), dbFile.getDescription(),dbFile.getStudentName());
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	
	
	@GetMapping("/submittedAssign/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId){
		StudentAssignment doc = asgService.getFile(fileId).get();
		System.out.println(doc.getName());
		System.out.println(doc.getType());
		return ResponseEntity.ok()
				//.contentType(MediaType.parseMediaType("png/pdf"))
				//.contentType(MediaType.parseMediaType("application/text"))
				.contentType(MediaType.parseMediaType(doc.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
	@DeleteMapping("/submittedAssign/{id}")
	public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String id){
		String message = "";
		
		try {
			asgService.deleteFile(id);
			message = "Deleted the file successfully " ;
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
//			e.printStackTrace();
			message = "Could not Delete the file";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
		
	}

}
