package com.microServices.StudentService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTableDTO {
  private Long id;
  private String name;
  private Integer batchId;
  private String academicYear;
  private String section;
  private String departement;
}
