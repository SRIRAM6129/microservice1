package com.mircroService.departmentService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  @GetMapping("/")
  public String greet(){
    return "DEPARTMENT SERVICE IS UP";
  }
}
