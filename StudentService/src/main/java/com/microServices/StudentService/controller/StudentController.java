package com.microServices.StudentService.controller;

import java.util.Collections;
import java.util.List;

import com.microServices.StudentService.dto.StudentDetailsDTO;
import com.microServices.StudentService.exception.StudentException;
import com.microServices.StudentService.model.StudentModel;
import com.microServices.StudentService.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

  private final Logger LOG = LoggerFactory.getLogger(StudentController.class);

  @Autowired
  private StudentService studentService;

  //DEFAULT CRUD OPERATIONS

  @GetMapping("/")
  public String server() {
    return "Student Server is Up";
  }

  @GetMapping("/all")
  public ResponseEntity<List<StudentDetailsDTO>> getAll() {
    try {
      return ResponseEntity.ok(studentService.getAllStudent());
    } catch (StudentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Collections.singletonList(StudentDetailsDTO.builder().build()));
    } catch (Exception e) {
      LOG.error("Error in the getAll : {} ", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonList(StudentDetailsDTO.builder().build()));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentDetailsDTO> getByStudentId(@PathVariable("id") Integer id) {
    try {
      return ResponseEntity.ok(studentService.getById(id));
    } catch (StudentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(StudentDetailsDTO.builder().build());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(StudentDetailsDTO.builder().build());
    }
  }

  @PostMapping("/add")
  public ResponseEntity<String> addStudent(@RequestBody StudentModel studentModel) {
    try {
      studentService.addNewStudent(studentModel);
      return ResponseEntity.ok("Student registered");
    } catch (StudentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(e.toString());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add new department");
    }
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateStudent(
      @RequestBody StudentModel studentModel) {
    try {
      studentService.updateStudent(studentModel);
      return ResponseEntity.ok("The Student with " + studentModel.getId() + " has updated");
    } catch (StudentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(e.toString());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
    try {
      studentService.deleteStudentById(id);
      return ResponseEntity.ok("Student : " + id + " removed");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Student not found");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
  }

  //OTHER SERVICES CALLS
  @GetMapping("/dept/{deptId}")
  public ResponseEntity<List<StudentDetailsDTO>> getStudentByDepartmentId(@PathVariable("deptId") Integer id) {
    try {
      return ResponseEntity.ok(studentService.getStudentByDepartmentId(id));
    } catch (StudentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Collections.singletonList(StudentDetailsDTO.builder().build()));
    } catch (Exception e) {
      LOG.error("Error in the getAll : {} ", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonList(StudentDetailsDTO.builder().build()));
    }
  }

}
