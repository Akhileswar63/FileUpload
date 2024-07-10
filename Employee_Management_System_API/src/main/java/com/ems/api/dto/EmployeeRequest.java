package com.ems.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeRequest {

	private String imgUrl;

	private LocalDate dob;

	private String firstName;

	private String secondName;

	private String gender;
	private String country;
	private LocalDateTime joingDate;
	private String maritalStatus;
	private LocalDate probationPeriedStart;
	private LocalDate probationPeriedEnd;

	private boolean Status;
	private String location;
	private String religion;
	private String address;
    private String reportingTo;

	private Integer pincode;
	private String state;
	private String district;
	private String city;
	private String phoneNumber;

	private String emailId;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
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

	public LocalDateTime getJoingDate() {
		return joingDate;
	}

	public void setJoingDate(LocalDateTime joingDate) {
		this.joingDate = joingDate;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public LocalDate getProbationPeriedStart() {
		return probationPeriedStart;
	}

	public void setProbationPeriedStart(LocalDate probationPeriedStart) {
		this.probationPeriedStart = probationPeriedStart;
	}

	public LocalDate getProbationPeriedEnd() {
		return probationPeriedEnd;
	}

	public void setProbationPeriedEnd(LocalDate probationPeriedEnd) {
		this.probationPeriedEnd = probationPeriedEnd;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
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

	
	
}
