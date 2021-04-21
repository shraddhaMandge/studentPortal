package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sms.entity.Instructor;
import com.sms.entity.User;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long>{

	@Query(value="select *from user where email=?1", nativeQuery = true)
	Instructor findByEmail(String email);
	
	
	//void saveAll(User user);
}
