package  com.microServices.StudentService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("STAFF-CLIENT")
public interface StaffClient {

  
}
