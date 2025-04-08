package com.microServices.StudentService.controller;

import java.util.Collections;
import java.util.List;

import com.microServices.StudentService.dto.StudentDetailsDTO;
import com.microServices.StudentService.dto.StudentTableDTO;
import com.microServices.StudentService.exception.StudentException;
import com.microServices.StudentService.model.StudentModel;
import com.microServices.StudentService.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

  private final Logger LOG = LoggerFactory.getLogger(StudentController.class);

  @Autowired
  private StudentService studentService;

  // DEFAULT CRUD OPERATIONS

  @GetMapping("/")
  public String server() {
    return "Student Server is Up";
  }

  @GetMapping("/all")
  public ResponseEntity<List<StudentModel>> getAll() {
    try {
      return ResponseEntity.ok(studentService.getAllStudent());
    } catch (StudentException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Collections.singletonList(StudentModel.builder().build()));
    } catch (Exception e) {
      LOG.error("Error in the getAll : {} ", e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonList(StudentModel.builder().build()));
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
      LOG.error("============================== {}", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add new Student");
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

  // OTHER SERVICES CALLS
  @GetMapping("/{deptId}/dept")
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

  /*
   * @GetMapping("/{batchId}/batch")
   * public ResponseEntity<List<ExampleDTO>>
   * getStudentByBatchId(@PathVariable("batchId") Integer batchId) {
   * try {
   * return ResponseEntity.ok(studentService.getStudentByBatch(batchId));
   * } catch (Exception e) {
   * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
   * .body(Collections.singletonList(ExampleDTO.builder().build()));
   * }
   * }
   * 
   * @GetMapping("/studentDetails")
   * public ResponseEntity<List<ExampleDTO>> studentDetails(
   * 
   * @RequestParam(required = false) String className,
   * 
   * @RequestParam(required = false) Integer batchId,
   * 
   * @RequestParam(required = false) String department) {
   * try {
   * return ResponseEntity.ok(studentService.getStudentByFilter(className,
   * batchId, department));
   * } catch (Exception e) {
   * LOG.error(
   * "=============================================++++++++++++++++++++++++++++++++++++++++++++++ { }"
   * , e);
   * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
   * .body(Collections.singletonList(ExampleDTO.builder().build()));
   * }
   * }
   */

  @GetMapping("/get/table")
  public ResponseEntity<List<StudentTableDTO>> getStudentsForTable(
      @RequestParam(required = true) Integer batchId,
      @RequestParam(required = true) Integer academicYear,
      @RequestParam(required = false,defaultValue = "0") Integer deptId,
      @RequestParam(required = false) Integer section) {
    try {
      return ResponseEntity.ok(studentService.getStudentsForTable(batchId, academicYear, deptId, section));
    } catch (Exception e) {
      LOG.error(e.toString());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonList(StudentTableDTO.builder().build()));
    }

  }
}
