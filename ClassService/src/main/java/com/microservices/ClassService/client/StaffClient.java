package com.microservices.ClassService.client;

import java.util.List;

import com.microservices.ClassService.dto.StaffDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("STAFF-SERVICE")
public interface StaffClient {

  @GetMapping("/staff/{classId}/class")
  public ResponseEntity<List<StaffDTO>> getStaffByClassId(@PathVariable("classId") Integer classId);

}
