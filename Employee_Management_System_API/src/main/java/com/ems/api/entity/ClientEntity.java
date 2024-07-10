package com.ems.api.entity;



import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class ClientEntity {
	
	@Id
	private int clientId;
	
	@NotBlank(message = "name is mandatory")
    @Size(max = 100, message = "name must be less than 100 characters")
	private String name;
	
	@NotBlank(message = "location is mandatory")
    @Size(max = 200, message = "location must be less than 200 characters")
	private String location;
	
	@NotBlank(message = "country is mandatory")
    @Size(max = 100, message = "country must be less than 100 characters")
	private String country;
	
	@NotBlank(message = "Address is mandatory")
    @Size(max = 200, message = "Address must be less than 200 characters")
	private String address;
	
	@NotBlank(message = "Phone number is mandatory")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a valid 10-digit number")
	private String phoneNumber;
	
	@NotBlank(message = "email is mandatory")
    @Email(message = "Email ID must be a valid email address")
	private String emailId;
	
	@NotBlank(message = "website url is mandatory")
    @Size(max = 200, message = "website url must be less than 200 characters")
	private String websiteURL;
	
	private boolean status;
 
	@NotBlank(message = "created by is mandatory")
    @Size(max = 100, message = "created by must be less than 100 characters")
	private String createdBy;

    @NotNull(message = "Created date is mandatory")
	private LocalDateTime createdAt;

	@NotBlank(message = "updated by is mandatory")
    @Size(max = 100, message = "updated by must be less than 100 characters")
	private String updatedBy;

    @NotNull(message = "updated date is mandatory")
	private LocalDateTime updatedAt;
	
    @OneToOne
    @JoinColumn(name="employee_id")
    private EmployeeEntity addedBy;
    
    @OneToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;
	
	@OneToMany
    private List<EmployeeEntity> employee;

	
	public ClientEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientEntity(int clientId,
			@NotBlank(message = "name is mandatory") @Size(max = 100, message = "name must be less than 100 characters") String name,
			@NotBlank(message = "location is mandatory") @Size(max = 200, message = "location must be less than 200 characters") String location,
			@NotBlank(message = "country is mandatory") @Size(max = 100, message = "country must be less than 100 characters") String country,
			@NotBlank(message = "Address is mandatory") @Size(max = 200, message = "Address must be less than 200 characters") String address,
			@NotBlank(message = "Phone number is mandatory") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a valid 10-digit number") String phoneNumber,
			@NotBlank(message = "email is mandatory") @Email(message = "Email ID must be a valid email address") String emailId,
			@NotBlank(message = "website url is mandatory") @Size(max = 200, message = "website url must be less than 200 characters") String websiteURL,
			boolean status,
			@NotBlank(message = "created by is mandatory") @Size(max = 100, message = "created by must be less than 100 characters") String createdBy,
			@NotNull(message = "Created date is mandatory") LocalDateTime createdAt,
			@NotBlank(message = "updated by is mandatory") @Size(max = 100, message = "updated by must be less than 100 characters") String updatedBy,
			@NotNull(message = "updated date is mandatory") LocalDateTime updatedAt, EmployeeEntity addedBy,
			DepartmentEntity departmentEntity, List<EmployeeEntity> employee) {
		super();
		this.clientId = clientId;
		this.name = name;
		this.location = location;
		this.country = country;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.websiteURL = websiteURL;
		this.status = status;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
		this.addedBy = addedBy;
		this.departmentEntity = departmentEntity;
		this.employee = employee;
	}





	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}



	public DepartmentEntity getDepartmentEntity() {
		return departmentEntity;
	}


	public void setDepartmentEntity(DepartmentEntity departmentEntity) {
		this.departmentEntity = departmentEntity;
	}


	public List<EmployeeEntity> getEmployee() {
		return employee;
	}

	public void setEmployee(List<EmployeeEntity> employee) {
		this.employee = employee;
	}




	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
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


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public EmployeeEntity getAddedBy() {
		return addedBy;
	}


	public void setAddedBy(EmployeeEntity addedBy) {
		this.addedBy = addedBy;
	}


	
	
	}

