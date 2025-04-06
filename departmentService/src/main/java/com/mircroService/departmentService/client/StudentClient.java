package com.mircroService.departmentService.client;

import java.util.List;

import com.mircroService.departmentService.dto.StudentDetailsDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("STUDENT-SERVICE")
public interface StudentClient {

  @GetMapping("/student/all")
  public ResponseEntity<List<StudentDetailsDTO>> getAll();

  @GetMapping("/{id}")
  public ResponseEntity<StudentDetailsDTO> getByStudenttId(@PathVariable("id") Integer id);


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable("id") int id);
}
