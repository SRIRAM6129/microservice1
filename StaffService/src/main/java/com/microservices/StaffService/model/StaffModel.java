package com.microservices.StaffService.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffModel {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(name = "Name", nullable = false)
  private String name;

  @Column(name = "Phone_number", nullable = false, unique = true)
  private Long phoneNumber;

  @Column(name = "Department_id", nullable = false)
  private Integer deptId;

  @ElementCollection
  @Column(name = "Section", nullable = false)
  private List<Integer> classesId;
}
