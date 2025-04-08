package com.microServices.StudentService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

  private Integer id;
  private String name;
  private Long registerNumber;
  private Integer section;
  private Integer department;
  private Integer batch;
  private String Year;
}
