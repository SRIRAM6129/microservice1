package com.mircroService.departmentService.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "department")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentModel {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "Dept_id", nullable = false)
  private Integer id;
  @Column(name = "Department_name", nullable = false, unique = true)
  private String name;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
