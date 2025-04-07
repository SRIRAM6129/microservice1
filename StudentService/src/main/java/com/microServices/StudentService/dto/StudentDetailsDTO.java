package com.microServices.StudentService.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailsDTO {
  private long registerNumber;
  private String name;
  private int deptId;
  private long phoneNumber;
  private String address;
  private int classId;
}
