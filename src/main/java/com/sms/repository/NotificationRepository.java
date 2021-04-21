package com.sms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sms.entity.Assignment;
import com.sms.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,String>{

		@Transactional
		@Query(value="select *from assignment where department=?1", nativeQuery = true)
	    List<Notification>findAllByDepartment(String department);
	
}
