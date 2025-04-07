package com.mircroService.departmentService.client;

import java.util.List;

import com.mircroService.departmentService.dto.ClassNameDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CLASS-SERVICE")
public interface ClassClient {

  @GetMapping("/class/{classId}/dept")
  public ResponseEntity<List<ClassNameDTO>> getClassByDeptId(@PathVariable("classId") Integer id);

}
