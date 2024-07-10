package com.ems.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.api.entity.EmployeeEntity;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
	
	Optional<EmployeeEntity> findByEmailId(String emailId);
	
	Optional<EmployeeEntity> findByPhoneNumber(String phoneNumber);
	
	Optional<EmployeeEntity>  findBySecondName(String secondName);

}
