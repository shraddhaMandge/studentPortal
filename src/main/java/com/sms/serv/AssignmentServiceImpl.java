package com.sms.serv;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sms.entity.Assignment;
import com.sms.repository.AssignmentRepository;


@Service
public class AssignmentServiceImpl  implements AssignmentService{
	
	
	  
	  private AssignmentRepository assignmentRepo;
	  
	  
	  @Autowired
	  public void setAssignmentRepo(AssignmentRepository assignmentRepo) {
		this.assignmentRepo = assignmentRepo;
	}

	@Override
	  public Assignment store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    Assignment FileDB = new Assignment(fileName, file.getContentType(), file.getBytes());

	    return assignmentRepo.save(FileDB);
	  }

	  @Override
	  public Stream<Assignment> getAllFiles(String department) {
	    return assignmentRepo.findAllByDepartment(department).stream();//.get();
	  }
	  
	  @Override
	  public Stream<Assignment> getAllFiles() {
	    return assignmentRepo.findAll().stream();
	  }

	@Override
	public Assignment addAssignment(Assignment assignment) {
		return assignmentRepo.save(assignment);
		
	}

	@Override
	public void deleteFile(String id) {
		
		assignmentRepo.deleteById(id);
		
	}

	@Override
	public Optional<Assignment> getFile(String fileId) {
		return assignmentRepo.findById(fileId);
	}



}
