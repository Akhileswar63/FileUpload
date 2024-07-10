package com.ems.api.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "Employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    private String imgUrl;

    private LocalDate dob;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 100, message = "First name must be less than 100 characters")
    private String firstName;

    @Size(max = 100, message = "Second name must be less than 100 characters")
    private String secondName;

    @NotBlank(message = "Gender is mandatory")
    private String gender;

    @NotBlank(message = "Country is mandatory")
    private String country;

    private LocalDateTime joiningDate;

    @NotBlank(message = "Marital status is mandatory")
    private String maritalStatus;

    private LocalDate probationPeriodStart;

    private LocalDate probationPeriodEnd;

    private boolean status;

    @NotBlank(message = "Location is mandatory")
    private String location;

    @NotBlank(message = "Religion is mandatory")
    private String religion;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotNull(message = "Pincode is mandatory")
    private Integer pincode;

    @NotBlank(message = "State is mandatory")
    private String state;

    @NotBlank(message = "District is mandatory")
    private String district;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a valid 10-digit number")
    private String phoneNumber;

    @NotBlank(message = "Email ID is mandatory")
    @Email(message = "Email ID must be a valid email address")
    private String emailId;

    private String createdBy;

    private LocalDateTime createdDate;

    private String updatedBy;
    
    private String reportingTo;

    private LocalDateTime updatedDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "branch_id")
    private BranchEntity branchEntity;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "designation_id")
    private DesignationEntity designationEntity;
    
    
    @ManyToOne
    @JoinColumn(name = "clientId")
    private ClientEntity client;
    
    

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public LocalDateTime getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDateTime joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public LocalDate getProbationPeriodStart() {
		return probationPeriodStart;
	}

	public void setProbationPeriodStart(LocalDate probationPeriodStart) {
		this.probationPeriodStart = probationPeriodStart;
	}

	public LocalDate getProbationPeriodEnd() {
		return probationPeriodEnd;
	}

	public void setProbationPeriodEnd(LocalDate probationPeriodEnd) {
		this.probationPeriodEnd = probationPeriodEnd;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public BranchEntity getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}

	public DepartmentEntity getDepartmentEntity() {
		return departmentEntity;
	}

	public void setDepartmentEntity(DepartmentEntity departmentEntity) {
		this.departmentEntity = departmentEntity;
	}

	public DesignationEntity getDesignationEntity() {
		return designationEntity;
	}

	public void setDesignationEntity(DesignationEntity designationEntity) {
		this.designationEntity = designationEntity;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}



	// designation

	// dept

	// orgnation
	// branch
	
	
	

	// reportingtO

	// client
    
    

}
