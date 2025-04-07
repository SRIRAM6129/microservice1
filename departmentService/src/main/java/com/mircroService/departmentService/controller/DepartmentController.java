package com.mircroService.departmentService.controller;

import java.util.Collections;
import java.util.List;

import com.mircroService.departmentService.dto.ClassNameDTO;
import com.mircroService.departmentService.dto.DepartmentAddDTO;
import com.mircroService.departmentService.dto.StudentDetailsDTO;
import com.mircroService.departmentService.model.DepartmentModel;
import com.mircroService.departmentService.service.DepartmentService;

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
@RequestMapping("/department")
public class DepartmentController {

  @Autowired
  private DepartmentService departmentService;

  //DEFAULT CRUD 
  @GetMapping("/")
  public String greet() {
    return "DEPARTMENT SERVICE IS UP";
  }

  @GetMapping("/all")
  public ResponseEntity<List<DepartmentModel>> getAll() {
    try {
      return ResponseEntity.ok(departmentService.getAllDepartment());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<DepartmentModel> getByDepartmentId(@PathVariable("id") int id) {
    try {
      return ResponseEntity.ok(departmentService.getById(id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<String> addDepartment(@RequestBody DepartmentAddDTO departmentName) {
    try {
      departmentService.addNewDepartment(departmentName.getName());
      return ResponseEntity.ok("The New department has been added succesfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add new department");
    }
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateDepartment(
      @RequestBody DepartmentModel departmentModel) {
    try {
      departmentService.updateDepartment(departmentModel);
      return ResponseEntity.ok("The department with " + departmentModel.getId() + " has been updated succesfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteDepartment(@PathVariable("id") int id) {
    try {
      departmentService.deleteDepartment(id);
      return ResponseEntity.ok("Department with " + id + " has been deleted succesfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
  }

  // OTHER SERVICE CALLS
  @GetMapping("/{deptId}/class")
  public ResponseEntity<List<ClassNameDTO>> getClassByDepartment(@PathVariable("deptId") Integer deptId) {
    try {
      return ResponseEntity.ok(departmentService.getClassByDepartment(deptId));
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonList(null));
    }
  }
}
