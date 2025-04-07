package com.mircroService.departmentService.client;

import java.util.List;

import com.mircroService.departmentService.dto.StudentDetailsDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("STUDENT-SERVICE")
public interface StudentClient {

  @GetMapping("/dept/{deptId}")
  public ResponseEntity<List<StudentDetailsDTO>> getStudentByDepartmentId(@PathVariable("deptId") Integer id);
}
