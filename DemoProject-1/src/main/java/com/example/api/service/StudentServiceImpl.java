package com.example.api.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.entity.BankDetailsEntity;
import com.example.api.entity.StudentEntity;
import com.example.api.entity.SubjectMarksAndMemoEntity;
import com.example.api.entity.TeacherEntity;
import com.example.api.repo.StudentRepo;
import com.example.api.repo.StudentsMarksRepo;
import com.example.api.repo.TeacherRepo;
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	
     private StudentRepo studentRepo;
	
	@Autowired
	private StudentsMarksRepo marksRepo;
	@Autowired
	private TeacherRepo teacherRepo;
	
	
	@Override
	public StudentEntity createStudent(StudentEntity student) {
	   
		if(student != null)
		{
		StudentEntity save = studentRepo.save(student);
		return save;
		}	
		return null;
	}

	@Override
	public String deleteById(Long id) {
		
		boolean status = studentRepo.existsById(id);
		if(status) 
		{
			studentRepo.deleteById(id);
			return "Record Deleted Sucessfully with the id " + id;
		}
		
		return  "Id" + id + " do not Exist " ;
	}

	@Override
	public List<StudentEntity> getAllStudents() {
		
	List<StudentEntity> findAll = studentRepo.findAll();
		
		if(findAll.isEmpty())
		{
			return null;
		}
		
		return findAll;
	}

	@Override
	public StudentEntity getByStudentId(Long id) {
		if(id != null)
		{
		 Optional<StudentEntity> findById = studentRepo.findById(id);
		         if(findById.isEmpty())
		         {
		        	 return null;
		         }
		         return findById.get();
		}
		
		
		
		return null;
	}

	@Override
	public StudentEntity getByEmailId(String email) {
	   Optional<StudentEntity> findByEmail = studentRepo.findByEmail(email);
	   if(findByEmail.isPresent())
	   {
		   StudentEntity studentEntity = findByEmail.get();
		   return studentEntity;
	   }
		return null;
	}

	@Override
	public String updateStudent(StudentEntity student) {
		if(student != null)
		{
			long id = student.getId();
			Optional<StudentEntity> findById = studentRepo.findById(id);
			if(findById.isPresent())
			{
				StudentEntity studentEntity = findById.get();
				studentRepo.save(student);
				return "Record Updated Sucessfully with name " + student.getName();
			}
			else
			{
				return " Record is Not Present with id "  +  student.getId() +  " or name "  + student.getName();
			}
		}
		return "try ro pass correct values for this method.";
	}

	@Override
	public StudentEntity createBankDetailsByStudent(Long id, BankDetailsEntity bankDetails) {
		Optional<StudentEntity> findById = studentRepo.findById(id);
		if(findById.isEmpty()) {
			return null;
		}
		StudentEntity studentEntity = findById.get();
		studentEntity.setBankDetails(bankDetails);
		studentRepo.save(studentEntity);
	    return studentEntity;
	}

	@Override
	public StudentEntity createMarks(Long id, List<SubjectMarksAndMemoEntity> marks) {
		Optional<StudentEntity>  findById = studentRepo.findById(id);
		if(findById.isEmpty()) {
			return null;
		}
		StudentEntity studentEntity = findById.get();
		for (SubjectMarksAndMemoEntity subjectMarksAndMemoEntity : marks) {
			subjectMarksAndMemoEntity.setStudent(studentEntity);
			marksRepo.save(subjectMarksAndMemoEntity);
		}
		studentEntity.setMarks(marks);
		
		return studentRepo.save(studentEntity);
	}

	@Override
	public TeacherEntity addTeacher(TeacherEntity teacher) {
		if(teacher!=null) {
			TeacherEntity teacherDetails = teacherRepo.save(teacher);
			return teacherDetails;
		}
		throw new RuntimeException("provide correct details to the record...");
	}

	@Override
	public StudentEntity assignTeacherToStudent(Long studentId, Long teacherId) {
		Optional<StudentEntity> studentDetails = studentRepo.findById(studentId);
		Optional<TeacherEntity> teacherDetails = teacherRepo.findById(teacherId);
		
		if(studentDetails .isEmpty()&& teacherDetails.isEmpty()) {
			throw new RuntimeException("student or teacher is not present in our records..."+studentId+"teacherId"+teacherId);
		}
		StudentEntity studentEntity = studentDetails .get();
		TeacherEntity teacherEntity = teacherDetails.get();
		Set<TeacherEntity> teacher= new HashSet<>();
		teacher.add(teacherEntity);
		studentEntity.setTeacher(teacher);
		StudentEntity save = studentRepo.save(studentEntity);
		return save;
	}

	@Override
	public TeacherEntity assignStudentToTeacher(Long teacherId, Long studentId) {
	    Optional<TeacherEntity> teacherDetails = teacherRepo.findById(teacherId);
	    Optional<StudentEntity> studentDetails = studentRepo.findById(studentId);
	    
	    if (teacherDetails.isEmpty() || studentDetails.isEmpty()) {
	        throw new RuntimeException("Teacher or student is not present in our records. teacherId: " + teacherId + ", studentId: " + studentId);
	    }
	    
	    StudentEntity studentEntity = studentDetails.get();
	    TeacherEntity teacherEntity = teacherDetails.get();
	    
	    Set<StudentEntity> students = teacherEntity.getStudent(); // Get the set of students from teacher
	    if (students == null) {
	        students = new HashSet<>(); // Initialize if null
	    }
	    students.add(studentEntity); // Add the student to the set
	    teacherEntity.setStudent(students); // Set the updated set of students
	    
	    TeacherEntity savedTeacher = teacherRepo.save(teacherEntity); // Save the updated teacher
	    return savedTeacher;
	}

	}

	

	





