package com.sms.serv;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Attendance;
import com.sms.repository.AttendanceRepository;

@Service
public class AttendanceServImpl implements AttendanceService {

	private AttendanceRepository attendaceRepo;

	@Autowired
	public void setAttendaceRepo(AttendanceRepository attendaceRepo) {
		this.attendaceRepo = attendaceRepo;
	}

	@Override
	public List<Attendance> findAll() {
		// TODO Auto-generated method stub
		return attendaceRepo.findAll();
	}

	@Override
	public List<Attendance> findByRollId(int id) {
		// TODO Auto-generated method stub
		return attendaceRepo.findByRollId(id);
	}

	@Override
	public void save(Attendance attendance) {

		attendaceRepo.save(attendance);

	}

	@Override
	public void deleteById(int id) {

		attendaceRepo.deleteById(id);
	}

	@Override
	public Attendance findById(int id) {

		Optional<Attendance> result = attendaceRepo.findById(id);

		Attendance attendance = null;

		if (result.isPresent()) {
			attendance = result.get();
		} else {
		
			throw new RuntimeException("Did not find  id - " + id);
		}

		return attendance;
	}

}
