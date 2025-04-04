package com.mircroService.departmentService.repository;

import com.mircroService.departmentService.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentModel,Long>{
  
}
