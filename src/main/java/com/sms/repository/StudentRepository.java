package com.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sms.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	@Query(value="select *from user where email=?1", nativeQuery = true)
	Student findByEmail(String email);

	@Query(value="select *from user where dept_id=?1", nativeQuery = true)
	List<Student> findAllByDepartment(int id);

//	void saveAll(User user);

}
