package com.microservices.StaffService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("CLASS-SERVICE")
public interface ClassClient {

  
}
