
package com.sms.cntr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sms.entity.Attendance;
import com.sms.entity.Status;
import com.sms.serv.AttendanceService;

@CrossOrigin("*")
@RestController
public class AttendanceCntr {


	private AttendanceService attendanceServ;

	@Autowired
	public void setAttendanceServ(AttendanceService attendanceServ) {
		this.attendanceServ = attendanceServ;
	}

	@GetMapping("/attendance")
	public List<Attendance> findAll() {
		return attendanceServ.findAll();
	}
	
	//list the attendence list of the user (using userid)
	@GetMapping("/attendance/{rollId}")
	public List<Attendance>getAttendance(@PathVariable int rollId) {
		
		List<Attendance> attendance = attendanceServ.findByRollId(rollId);
		
		if (attendance == null) {
			throw new RuntimeException("roll id not found - " + rollId);
		}
		
		return attendance;
	}
	
	//get by attendance id 
	@GetMapping("/attendanceByAttId/{rollId}")
	public Attendance getAttendanceById(@PathVariable int rollId) {
		
		Attendance attendance = attendanceServ.findById(rollId);
		
		if (attendance == null) {
			throw new RuntimeException("roll id not found - " + rollId);
		}
		
		return attendance;
	}
	
	
	@PostMapping("/attendance")
	public Status addEmployee(@RequestBody Attendance attendance) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		Status sts = new Status("failed " , false);
		attendance.setId(0);
		try {
		attendanceServ.save(attendance);
			sts.setMsg("added");
			sts.setStatus(true);
		}catch(Exception e)
		{
			sts.setMsg("Bad Credentials");
		}
		
		return sts;
	}
	
	
	@PutMapping("/attendance")
	public Status updateEmployee(@RequestBody Attendance attendance) {
		Status sts = new Status("failed " , false);
		try {
			attendanceServ.save(attendance);
			sts.setMsg("updated ...");
			sts.setStatus(true);
			}catch(Exception e)
			{
				sts.setMsg("Bad Credentials");
			}
			
			return sts;
	}
	
	
	@DeleteMapping("/attendance/{attendanceId}")
	public Status deleteAttendance(@PathVariable int attendanceId) {
		
		Status sts = new Status("Deletion success " , true);
		Attendance tempEmployee = attendanceServ.findById(attendanceId);
		
		// throw exception if null
		
		if (tempEmployee == null) {
			throw new RuntimeException("attendance id not found - " + attendanceId);
		}
		
		attendanceServ.deleteById(attendanceId);
		
		return sts;
	}
}
