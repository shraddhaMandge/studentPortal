package com.sms.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sms.entity.StudentAssignment;

@Repository
public interface AssignmentSubmittedRepository extends JpaRepository<StudentAssignment, String>{
	
	Optional<StudentAssignment> findById(String id);

	//@Modifying
    @Transactional
	@Query(value="select *from studentassignment where department=?1", nativeQuery = true)
    List<StudentAssignment>findAllByDepartment(String department);

}
