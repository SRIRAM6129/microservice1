package com.microServices.StudentService.client;

import java.util.List;

import com.microServices.StudentService.dto.DepartmentDropDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("DEPARTMENT-SERVICE")
public interface DepartmentClient {
  @GetMapping("/department/{id}/name")
  public ResponseEntity<String> getDepartmentName(@PathVariable("id") Integer id);

  @GetMapping("/department/{deptname}/id")
  public ResponseEntity<Integer> getDepartmentId(@PathVariable("deptname") String deptName);

  @GetMapping("/department/all/name")
  public ResponseEntity<List<DepartmentDropDTO>> getDepartmentName();
  
}
