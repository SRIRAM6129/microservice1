package com.microservices.ClassService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("STUDENT-SERVICE")
public interface StudentClient {

  
}
