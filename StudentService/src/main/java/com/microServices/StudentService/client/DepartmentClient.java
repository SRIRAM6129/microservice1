package com.microServices.StudentService.client;

import java.util.List;

import com.microServices.StudentService.dto.DepartmentDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("DEPARTMENT-SERVICE")
public interface DepartmentClient {

  @GetMapping("/all")
  public ResponseEntity<List<DepartmentDTO>> getAll();

  @GetMapping("/{id}")
  public ResponseEntity<DepartmentDTO> getByDepartmentId(@PathVariable("id") int id);
}
