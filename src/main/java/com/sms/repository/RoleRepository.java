package com.sms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sms.entity.Role;



@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	
	//Role findByname(String name);

}
