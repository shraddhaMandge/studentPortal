package com.sms.cntr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sms.entity.Marksheet;
import com.sms.serv.MarksheetService;

@RestController
public class MarksheetCntr {

	private MarksheetService marksheetServ;

	@Autowired
	public void setMarkServ(MarksheetService markServ) {
		this.marksheetServ = markServ;
	}
	
	@PostMapping("/marksheet")
	public String createMarksheet(@RequestBody Marksheet marksheet)
	{
		String msg="Marksheet Not Created";
		try {
			int obtainMark=0;
			//double percentage=0;
			if(marksheet.getDepartment().equals("Dac"))// should we give obtain or total marks manually ??
			{
				obtainMark=marksheet.getAngular()+marksheet.getDatastructure();
			}else
			{
				obtainMark=marksheet.getSpringboot()+marksheet.getJava();
			}
			marksheet.setObtainmarks(obtainMark);
			double	percentage=(double)(obtainMark*100)/marksheet.getTotalmarks();	
			marksheet.setPercentage(percentage);
			marksheetServ.creatMarksheet(marksheet);
			msg="Marksheet Generated";
		}
		catch(Exception e)
		{
			return "Bad Credentails";
		}
		
		return msg;
	}
	
	@GetMapping("/marksheet/{userId}")
	public Marksheet getMarksheet(@PathVariable long userId)
	{
		try
		{
			return marksheetServ.getMarksheet(userId);
		}
		catch(Exception e)
		{
			return null;// if return null thats means roll no is not valid;		
		}
		
		
	}
	
	
	
	
}
