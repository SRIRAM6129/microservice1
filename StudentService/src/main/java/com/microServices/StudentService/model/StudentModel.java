package com.microServices.StudentService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "Register_number", nullable = false, unique = true)
  private Long registerNumber;

  @Column(name = "Name", nullable = false)
  private String name;

  @Column(name = "Password", nullable = false)
  private String password;

  @Column(name = "phoneNumber", nullable = true)
  private Long phoneNumber;

  @Column(name = "Address", nullable = false)
  private String address;

  @Column(name = "Department_id", nullable = false)
  private Integer deptId;

  @Column(name = "Class_id", nullable = false)
  private Integer classId;
}
