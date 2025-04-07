package com.microServices.StudentService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("CLASS-SERVICE")
public interface ClassClient {

  
}
