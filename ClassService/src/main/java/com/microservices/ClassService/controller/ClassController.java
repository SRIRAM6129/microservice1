package com.microservices.ClassService.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.microservices.ClassService.dto.ClassDropDTO;
import com.microservices.ClassService.model.ClassModel;
import com.microservices.ClassService.service.ClassService;

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
@RequestMapping("/class")
@CrossOrigin
public class ClassController {

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

  // OTHER SERVICES
  @GetMapping("/{id}/name")
  public ResponseEntity<String> getClassNameById(@PathVariable("id") Integer id) {
    try {
      return ResponseEntity.ok(classService.getClassNameById(id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @GetMapping("/dept")
  public ResponseEntity<List<ClassDropDTO>> getClassNameByDeptId(@RequestParam(required = false) Integer deptId) {
    try {
      if (deptId == 0) {
        return ResponseEntity.ok(
            classService.getAll().stream()
                .map((cl) -> ClassDropDTO.builder().id(cl.getId()).name(cl.getSection()).build())
                .collect(Collectors.toList()));
      }
      return ResponseEntity.ok(classService.getClassNameByDeptId(deptId));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @GetMapping("/all/name")
  public ResponseEntity<List<String>> getClassName() {
    try {
      return ResponseEntity.ok(classService.getClassName());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList(e.toString()));
    }
  }


}
