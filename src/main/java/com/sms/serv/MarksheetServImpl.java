package com.sms.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Marksheet;
import com.sms.entity.User;
import com.sms.repository.MarksheetRepository;

@Service
public class MarksheetServImpl implements MarksheetService {

	private MarksheetRepository marksheetRepo;
	
	
	
	@Autowired
	public void setMarksheetRepo(MarksheetRepository marksheetRepo) {
		this.marksheetRepo = marksheetRepo;
	}

	@Override
	public Marksheet creatMarksheet(Marksheet marksheet) {

		
		
		return marksheetRepo.save(marksheet) ;
	}

	@Override
	public Marksheet getMarksheet(long id) throws RuntimeException{
		Marksheet result = marksheetRepo.findByUserId(id);
		Marksheet marksheet = null;
		
		if (result!=null) {
			marksheet = result;
		}
		else {
			throw new RuntimeException("UserId not exist ");
		}
		return marksheet ;
	
	}

	
}
