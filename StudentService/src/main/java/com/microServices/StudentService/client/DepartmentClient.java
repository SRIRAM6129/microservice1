package com.microServices.StudentService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("DEPARTMENT-SERVICE")
public interface DepartmentClient {
}
