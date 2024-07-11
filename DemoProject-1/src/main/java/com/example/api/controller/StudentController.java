package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.entity.BankDetailsEntity;
import com.example.api.entity.StudentEntity;
import com.example.api.entity.SubjectMarksAndMemoEntity;
import com.example.api.entity.TeacherEntity;
import com.example.api.exception.StudentNotFoundException;
import com.example.api.service.StudentService;

import jakarta.websocket.server.PathParam;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/greet")
    public String greetMsg() {
        return "Anarghya Communications";
    }

    @PostMapping("/marks/{id}")
    public ResponseEntity<StudentEntity> createMarks(@PathVariable Long id, @RequestBody List<SubjectMarksAndMemoEntity> Marks) {
        StudentEntity createMarks = service.createMarks(id, Marks);
        if (createMarks == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(createMarks, HttpStatus.CREATED);
    }

    @PostMapping("/create/bank/{id}")
    public ResponseEntity<StudentEntity> createBankDetailsByStudent(@PathVariable Long id, @RequestBody BankDetailsEntity bankDetails) {
        StudentEntity createBankDetailsByStudent = service.createBankDetailsByStudent(id, bankDetails);
        if (createBankDetailsByStudent == null) {
            return new ResponseEntity<>(createBankDetailsByStudent, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createBankDetailsByStudent, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity student) {
        StudentEntity createStudent = service.createStudent(student);
        if (createStudent == null) {
            return new ResponseEntity<>(createStudent, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        String message = service.deleteById(id);
        if (message.contains("Record deleted successfully with the id " + id)) {
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> allStudents = service.getAllStudents();
        if (allStudents == null) {
            return new ResponseEntity<>(allStudents, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allStudents);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<StudentEntity> getByStudentId(@PathVariable(value = "id") Long id) {
        StudentEntity byStudentId = service.getByStudentId(id);
        if (byStudentId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byStudentId);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<StudentEntity> getByEmailId(@PathVariable String email) {
        StudentEntity byEmailId = service.getByEmailId(email);
        if (byEmailId == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(byEmailId, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStudent(@RequestBody StudentEntity student) {
        String updateStudent = service.updateStudent(student);
        if (updateStudent.contains("Record is Not Present with id " + student.getId() + " or name " + student.getName())
                || updateStudent.contains("try to pass correct values for this method.")) {
            return new ResponseEntity<>(updateStudent, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(updateStudent);
    }

    @PostMapping("/create/teacher")
    public ResponseEntity<TeacherEntity> addTeacher(@RequestBody TeacherEntity teacher) {
        try {
            TeacherEntity addTeacher = service.addTeacher(teacher);
            return new ResponseEntity<>(addTeacher, HttpStatus.CREATED);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/assign/TeacherToStudent/{studentId}/{teacherId}")
    public ResponseEntity<StudentEntity> assignTeacherToStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        try {
            StudentEntity student = service.assignTeacherToStudent(studentId, teacherId);
            return new ResponseEntity<>(student, HttpStatus.ACCEPTED);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/assign/StudentToTeacher/{teacherId}/{studentId}")
    public ResponseEntity<TeacherEntity> assignStudentToTeacher(@PathVariable Long teacherId, @PathVariable Long studentId) {
        try {
            TeacherEntity teacher = service.assignStudentToTeacher(teacherId, studentId);
            return new ResponseEntity<>(teacher, HttpStatus.ACCEPTED);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
