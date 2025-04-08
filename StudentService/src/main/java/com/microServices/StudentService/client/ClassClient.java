package com.microServices.StudentService.client;

import java.util.List;

import com.microServices.StudentService.dto.ClassDropDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("CLASS-SERVICE")
public interface ClassClient {

  @GetMapping("/class/{id}/name")
  public ResponseEntity<String> getClassName(@PathVariable("id") Integer id);

  @GetMapping("/class/{id}/name")
  public ResponseEntity<String> getClassNameById(@PathVariable("id") Integer id);

  @GetMapping("/class/dept")
  public ResponseEntity<List<ClassDropDTO>> getClassNameByDeptId(@RequestParam(required = false) Integer deptId);

}
