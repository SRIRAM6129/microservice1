package com.microservices.StaffService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("STUDENT-SERVICE")
public interface StudentClient {

  
}
