package com.project.file.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.file.entity.FilesEntity;

@Repository
public interface FilesRepo extends JpaRepository<FilesEntity, Long> {

}
