package com.microservices.ClassService.controller;

import java.util.Collections;
import java.util.List;

import com.microservices.ClassService.dto.StudentDetailsDTO;
import com.microservices.ClassService.exception.ClassException;
import com.microservices.ClassService.model.ClassModel;
import com.microservices.ClassService.service.ClassService;

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
@RequestMapping("/class")
public class ClassController {

  private final Logger LOG = LoggerFactory.getLogger(ClassController.class);

  @Autowired
  private ClassService classService;

  // DEFAULT CRUD OPERATIONS
  @GetMapping("/")
  public String server() {
    return "Class Server is Up";
  }

  @GetMapping("/all")
  public ResponseEntity<List<ClassModel>> getAll() {
    try {
      return ResponseEntity.ok(classService.getAll());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClassModel> getClassById(@PathVariable("id") Integer id) {
    try {
      return ResponseEntity.ok(classService.getById(id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<String> addClass(@RequestBody ClassModel classModel) {
    try {
      classService.addClass(classModel);
      return ResponseEntity.ok("Class added succesfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateClass(@RequestBody ClassModel classModel) {
    try {
      classService.updateClass(classModel);
      return ResponseEntity.ok("Class updated succesfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteClass(@PathVariable("id") int id) {
    try {
      classService.deleteClass(id);
      return ResponseEntity.ok("Class deleted succesfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }
  //OTHER SERVICES
}
