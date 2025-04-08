package com.microservices.ClassService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("STAFF-SERVICE")
public interface StaffClient {

}
