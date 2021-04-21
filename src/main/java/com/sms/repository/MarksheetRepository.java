package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sms.entity.Marksheet;

public interface MarksheetRepository extends JpaRepository<Marksheet,Long> {

	
	
	@Query(value="select *from Marksheet where user_id=?1", nativeQuery = true)
	Marksheet findByUserId(long user_id);
		
}
