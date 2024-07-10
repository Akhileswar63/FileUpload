package com.ems.api.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "organization")
public class OrganizationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int organizationId;

	@NotBlank(message = "Name is mandatory")
	@Size(max = 100, min = 3, message = "Name can have a maximum of 255 characters")
	private String name;

	private boolean status;

	private String createdBy;

	private LocalDateTime createdAt;

	private String updatedBy;

	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<BranchEntity> branches;
	
	
	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
	@JsonManagedReference
	private  List<EmployeeEntity> employeeEntity;

	public List<EmployeeEntity> getEmployeeEntity() {
		return employeeEntity;
	}

	public void setEmployeeEntity(List<EmployeeEntity> employeeEntity) {
		this.employeeEntity = employeeEntity;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public Set<BranchEntity> getBranches() {
		return branches;
	}

	public void setBranches(Set<BranchEntity> branches) {
		this.branches = branches;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	

}
