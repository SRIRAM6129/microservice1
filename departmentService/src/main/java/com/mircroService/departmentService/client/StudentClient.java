package com.mircroService.departmentService.client;

import java.util.List;

import com.mircroService.departmentService.dto.StudentDetailsDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("STUDENT-SERVICE")
public interface StudentClient {

  @GetMapping("/student/all")
  public ResponseEntity<List<StudentDetailsDTO>> getAll();

  @GetMapping("/{id}")
  public ResponseEntity<StudentDetailsDTO> getByStudentId(@PathVariable("id") Integer id);

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable("id") int id);

  @GetMapping("/student/dept/{deptId}")
  public ResponseEntity<List<StudentDetailsDTO>> getStudentByDepartmentId(@PathVariable("deptId") Integer id);

}
