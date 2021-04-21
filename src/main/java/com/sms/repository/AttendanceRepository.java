package com.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sms.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
	
	
	@Query(value="select*  from attendance where user_id=?1", nativeQuery = true)
	List<Attendance> findByRollId(int id);

}
