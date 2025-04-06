package com.microservices.ClassService.repository;

import java.util.List;
import java.util.Optional;

import com.microservices.ClassService.model.ClassModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository  extends JpaRepository<ClassModel,Integer>{

    Optional<List<ClassModel>> findByDeptId(Integer id);

}
