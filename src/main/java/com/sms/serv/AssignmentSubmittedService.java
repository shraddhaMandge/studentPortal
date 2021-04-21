package com.sms.serv;

import java.util.Optional;
import java.util.stream.Stream;

import com.sms.entity.StudentAssignment;

public interface AssignmentSubmittedService {

	public StudentAssignment addAssignment(StudentAssignment assignment);
	
	public Optional<StudentAssignment> getFile(String fileId); 
	
	public Stream<StudentAssignment> getAllFiles();
	
	public void deleteFile(String id);
}
