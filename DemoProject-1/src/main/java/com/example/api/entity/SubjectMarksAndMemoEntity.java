package com.example.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class SubjectMarksAndMemoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String examname;
private String maths;
private String statistics;
private String cs;
@ManyToOne(cascade = CascadeType.ALL)
@JsonIgnore
private StudentEntity student;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getExamname() {
	return examname;
}
public void setExamname(String examname) {
	this.examname = examname;
}
public String getMaths() {
	return maths;
}
public void setMaths(String maths) {
	this.maths = maths;
}
public String getStatistics() {
	return statistics;
}
public void setStatistics(String statistics) {
	this.statistics = statistics;
}
public String getCs() {
	return cs;
}
public void setCs(String cs) {
	this.cs = cs;
}
public StudentEntity getStudent() {
	return student;
}
public void setStudent(StudentEntity student) {
	this.student = student;
}




}
