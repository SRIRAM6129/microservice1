package com.microservices.ClassService.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
  private Integer id;
  private String name;
  private Long phoneNumber;
  private Integer deptId;

}
