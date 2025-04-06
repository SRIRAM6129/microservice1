package com.microservices.ClassService.service;

import java.util.List;
import java.util.stream.Collectors;

import com.microservices.ClassService.client.StaffClient;
import com.microservices.ClassService.dto.ClassNameDTO;
import com.microservices.ClassService.dto.StaffDTO;
import com.microservices.ClassService.exception.ClassException;
import com.microservices.ClassService.model.ClassModel;
import com.microservices.ClassService.repository.ClassRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

  @Autowired
  private ClassRepository classRepository;

  @Autowired
  private StaffClient staffClient;

  public List<ClassNameDTO> getByDeptId(Integer id) throws ClassException {
    List<ClassModel> classes = classRepository.findByDeptId(id).orElseThrow(() -> new ClassException(""));
    return classes
        .stream()
        .map((cl) -> ClassNameDTO.builder().name(cl.getName()).build())
        .collect(Collectors.toList());
  }

  public List<StaffDTO> getStaffByClass(Integer id) {
    return staffClient.getStaffByClassId(id).getBody();
  }
}
