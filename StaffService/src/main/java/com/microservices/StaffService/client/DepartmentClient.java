package com.microservices.StaffService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("DEPARTMENT-SERVICE")
public interface DepartmentClient {

  
}
