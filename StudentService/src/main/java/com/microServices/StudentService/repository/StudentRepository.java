package com.microServices.StudentService.repository;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import com.microServices.StudentService.dto.StudentDetailsDTO;
import com.microServices.StudentService.model.StudentModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {
  Optional<StudentModel> findById(Long id);

  Optional<List<StudentModel>> findByDeptId(Integer id);
}
