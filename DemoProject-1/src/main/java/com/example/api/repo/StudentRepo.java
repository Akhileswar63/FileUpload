package com.example.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.entity.StudentEntity;


@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Long> {
	
         public  Optional<StudentEntity> findByEmail(String email);
}
