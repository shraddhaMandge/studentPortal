package com.sms.serv;

import java.util.List;

import com.sms.entity.Attendance;

public interface AttendanceService {
	
	
public List<Attendance> findAll();
	
	public List<Attendance> findByRollId(int rollId);
	
	public void save(Attendance attendance);
	
	public void deleteById(int theId);

	public Attendance findById(int attendanceId);

}
