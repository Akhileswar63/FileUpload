package com.example.api.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "Students_info")
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;
	private String name;
    private String email;
    private String pazz;
    private Long phoneNo;
    private String rollNo;
    private String dept;
    
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bank_Id")
    private BankDetailsEntity bankDetails;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "student")
	private List<SubjectMarksAndMemoEntity> marks;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(name="student_teacher_mapping", joinColumns = @JoinColumn(name = "student_Id"),
	inverseJoinColumns =@JoinColumn(name="teacher_id") )
	private Set<TeacherEntity> teacher;
    
         public Set<TeacherEntity> getTeacher() {
		return teacher;
	}
	public void setTeacher(Set<TeacherEntity> teacher) {
		this.teacher = teacher;
	}
		public List<SubjectMarksAndMemoEntity> getMarks() {
		return marks;
	}
	public void setMarks1(List<SubjectMarksAndMemoEntity> marks) {
		this.marks = marks;
	}
		public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPazz() {
		return pazz;
	}
	public void setPazz(String pazz) {
		this.pazz = pazz;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
		
	 public BankDetailsEntity getBankDetails() {
			return bankDetails;
		}
		public void setBankDetails(BankDetailsEntity bankDetails) {
			this.bankDetails = bankDetails;
		}
		public void setMarks(List<SubjectMarksAndMemoEntity> marks) {
			// TODO Auto-generated method stub
			
		}
		public void setTeacher1(Set<TeacherEntity> teacher2) {
			// TODO Auto-generated method stub
			
		}
		
		
	
         
         
}

