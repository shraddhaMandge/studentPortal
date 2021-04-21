package com.sms.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	//@Query(value="select (password,first_name,last_name,email,phone, department_name )from User LEFT JOIN department ON where User.dept_id=department.dept_id and email=?1", nativeQuery = true)
	@Query(value="select*  from User where email=?1", nativeQuery = true)
	User findByEmail(String email);
	
    @Modifying
    @Transactional
	@Query(value="delete  from User where email=?1", nativeQuery = true)
	void deleteByEmail(@Param("email") String email);
    
    //check 
    @Query(" from User where dept_id =:dept_id AND role_id =:role_id")
    Iterable<User> findByDeptAndRole(@Param("dept_id") int dept_id,@Param("role_id") int role_id);

    @Query(" from User where email =:email AND password =:password")
    Optional<User> findByEmailAndPassword(@Param("email") String email,@Param("password") String password);

    
}

//select user_id,password,department_name from User LEFT JOIN department ON User.dept_id=department.dept_id