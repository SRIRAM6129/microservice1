package com.mircroService.departmentService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("STAFF-SERVICE")
public interface StaffClient {
  
}
