package com.example.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.api.entity.SubjectMarksAndMemoEntity;
@Repository
public interface StudentsMarksRepo extends JpaRepository<SubjectMarksAndMemoEntity,Long> {


}
