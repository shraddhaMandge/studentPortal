package com.sms.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sms.entity.Assignment;


@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,String> {

	Optional<Assignment> findById(String id);

		//@Modifying
	    @Transactional
		@Query(value="select *from assignment where department=?1", nativeQuery = true)
	    List<Assignment>findAllByDepartment(String department);

}
