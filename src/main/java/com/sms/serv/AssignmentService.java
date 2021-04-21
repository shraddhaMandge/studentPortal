package com.sms.serv;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.sms.entity.Assignment;


public interface AssignmentService {
	
	public Stream<Assignment> getAllFiles(String department);
	
	public Stream<Assignment> getAllFiles();
	
	public Assignment store(MultipartFile filefile ) throws IOException;

	public Assignment addAssignment(Assignment assignment);

	public void deleteFile(String id);
	
	public Optional<Assignment> getFile(String fileId); 
	
}
