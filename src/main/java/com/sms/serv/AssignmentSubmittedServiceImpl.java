package com.sms.serv;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.StudentAssignment;
import com.sms.repository.AssignmentSubmittedRepository;

@Service
public class AssignmentSubmittedServiceImpl implements AssignmentSubmittedService{
	
	@Autowired
	private AssignmentSubmittedRepository submitAssignment;

	@Override
	public StudentAssignment addAssignment(StudentAssignment assignment) {
		return submitAssignment.save(assignment);
	}

	@Override
	public Optional<StudentAssignment> getFile(String fileId) {
		return submitAssignment.findById(fileId);
	}

	@Override
	public Stream<StudentAssignment> getAllFiles() {
		 return submitAssignment.findAll().stream();
	}

	@Override
	public void deleteFile(String id) {
		submitAssignment.deleteById(id);		
	}

}
