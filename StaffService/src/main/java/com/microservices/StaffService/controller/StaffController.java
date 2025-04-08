package com.microservices.StaffService.controller;

import java.util.List;

import com.microservices.StaffService.model.StaffModel;
import com.microservices.StaffService.service.StaffService;

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
@RequestMapping("/staff")
public class StaffController {

  @Autowired
  public StaffService staffService;

  @GetMapping("/")
  public String greet() {
    return "The staff server is running";
  }

  // DEFAULT CRUD OPERATION
  @GetMapping("/all")
  public ResponseEntity<List<StaffModel>> getAll() {
    try {
      return ResponseEntity.ok(staffService.getAll());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<StaffModel> getStaffById(@PathVariable("id") Integer id) {
    try {
      return ResponseEntity.ok(staffService.getById(id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<String> addStaff(@RequestBody StaffModel staffModel) {
    try {
      staffService.addStaff(staffModel);
      return ResponseEntity.ok("Staff added succesfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateStaff(@RequestBody StaffModel staffModel) {
    try {
      staffService.updateStaff(staffModel);
      return ResponseEntity.ok("Staff updated succesfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStaff(@PathVariable("id") int id) {
    try {
      staffService.deleteStaff(id);
      return ResponseEntity.ok("Staff deleted succesfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
    }
  }

}
