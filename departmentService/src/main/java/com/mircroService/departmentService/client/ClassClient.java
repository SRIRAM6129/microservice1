package com.mircroService.departmentService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("CLASS-SERVICE")
public interface ClassClient {
}
