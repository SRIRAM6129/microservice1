package com.microservices.ClassService.controller;

import java.util.Collections;
import java.util.List;

import com.microservices.ClassService.dto.ClassDetailsDTO;
import com.microservices.ClassService.dto.ClassNameDTO;
import com.microservices.ClassService.dto.StaffDTO;
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

  @GetMapping("/")
  public String server() {
    return "Class Server is Up";
  }

  @GetMapping("/{classId}/dept")
  public ResponseEntity<List<ClassNameDTO>> getClassByDeptId(@PathVariable("classId") Integer id) {
    try {
      return ResponseEntity.ok(classService.getByDeptId(id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonList(ClassNameDTO.builder().build()));
    }
  }

  @GetMapping("/{id}/staff")
  public ResponseEntity<List<StaffDTO>> getStaffByClass(@PathVariable("id") Integer id){
    try {
      return ResponseEntity.ok(classService.getStaffByClass(id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonList(StaffDTO.builder().build()));
    }
  }
}
