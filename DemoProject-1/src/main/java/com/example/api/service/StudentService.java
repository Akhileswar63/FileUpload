package com.example.api.service;

import java.util.List;

import com.example.api.entity.BankDetailsEntity;
import com.example.api.entity.StudentEntity;
import com.example.api.entity.SubjectMarksAndMemoEntity;
import com.example.api.entity.TeacherEntity;

public interface StudentService {
       public  StudentEntity createStudent(StudentEntity student);
       
       public String  deleteById(Long id);
        
       public List<StudentEntity> getAllStudents();
       
       public StudentEntity getByStudentId(Long id);
       
       public StudentEntity getByEmailId(String email);
       
       public String updateStudent(StudentEntity student);

	

	public StudentEntity createBankDetailsByStudent(Long id, BankDetailsEntity bankDetails);
	public StudentEntity createMarks(Long id,List<SubjectMarksAndMemoEntity>marks);
	public TeacherEntity addTeacher(TeacherEntity teacher);
	public StudentEntity assignTeacherToStudent(Long studentId,Long teacherId);
	public TeacherEntity assignStudentToTeacher(Long teacherId,Long studentId);
	
	
}

