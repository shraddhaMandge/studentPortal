package com.sms.cntr;

import java.util.List;
import java.util.stream.Collectors;

import javax.print.Doc;

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
import com.sms.serv.AssignmentService;

@RestController
@CrossOrigin("*")
public class AssignmentCntr {

	private AssignmentService asgService;

	@Autowired
	public void setAsgService(AssignmentService asgService) {
		this.asgService = asgService;
	}

	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(
			@RequestParam(value = "File", required = false) MultipartFile file,
			@RequestParam(required = true, value = "asgJson") String asgJson) {
		String message = "";
		try {
			//asgService.store(file);

			Assignment assignment = objectMapper.readValue(asgJson, Assignment.class);
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

	@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = asgService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(dbFile.getId()).toUriString();

			return new ResponseFile(dbFile.getId(),dbFile.getName(), fileDownloadUri, dbFile.getType(),
					// dbFile.getData().length,
					dbFile.getDepartment(), dbFile.getCourseName(), dbFile.getDescription());
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/filess/{department}")
	public ResponseEntity<List<ResponseFile>> getFiles(@PathVariable String department) {
		List<ResponseFile> files = asgService.getAllFiles(department).map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(dbFile.getId()).toUriString();

			return new ResponseFile(dbFile.getId(),dbFile.getName(), fileDownloadUri, dbFile.getType(),
					// dbFile.getData().length,
					dbFile.getDepartment(), dbFile.getCourseName(), dbFile.getDescription());
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	@DeleteMapping("/files/{id}")
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
	
	@GetMapping("/files/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId){
		Assignment doc = asgService.getFile(fileId).get();
		System.out.println(doc.getName());
		System.out.println(doc.getType());
		return ResponseEntity.ok()
				//.contentType(MediaType.parseMediaType("png/pdf"))
				//.contentType(MediaType.parseMediaType("application/text"))
				.contentType(MediaType.parseMediaType(doc.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	
	
	
}
