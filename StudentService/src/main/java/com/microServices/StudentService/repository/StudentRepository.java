package com.microServices.StudentService.repository;

import java.util.Optional;

import com.microServices.StudentService.model.StudentModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {
  Optional<StudentModel> findById(Long id);
}
