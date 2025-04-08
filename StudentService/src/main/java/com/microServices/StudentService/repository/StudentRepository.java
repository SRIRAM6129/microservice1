package com.microServices.StudentService.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.microServices.StudentService.model.StudentModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

  @Query("SELECT s FROM StudentModel s " +
      "WHERE s.batchId = :batchId AND s.academicYear = :academicYear " +
      "AND (:deptId IS NULL OR s.deptId = :deptId) " +
      "AND (:classId IS NULL OR s.classId = :classId)")
  List<StudentModel> findByBatchAndAcademicYearWithOptionalFilters(
      @Param("batchId") Integer batchId,
      @Param("academicYear") Integer academicYear,
      @Param("deptId") Integer deptId,
      @Param("classId") Integer classId);

  Optional<StudentModel> findById(Long id);

  Optional<List<StudentModel>> findByDeptId(Integer id);

  Optional<List<StudentModel>> findByBatchId(Integer batch);
}
