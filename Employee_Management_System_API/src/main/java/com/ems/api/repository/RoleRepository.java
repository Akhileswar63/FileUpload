package com.ems.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.api.entity.RoleEntity;

@Repository
public interface RoleRepository  extends JpaRepository<RoleEntity, Integer>{

}
