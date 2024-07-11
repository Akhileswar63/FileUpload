package com.example.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.entity.TeacherEntity;
@Repository
public interface TeacherRepo extends JpaRepository<TeacherEntity, Long>{

}
