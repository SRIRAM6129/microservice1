package com.mircroService.departmentService.service;

import java.util.List;

import com.mircroService.departmentService.model.DepartmentModel;
import com.mircroService.departmentService.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

  @Autowired
  private DepartmentRepository departmentRepository;

  public List<DepartmentModel> getAllDepartment() {
    return departmentRepository.findAll();
  }

  public DepartmentModel getById(int id) {
    return departmentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("The department not found"));
  }

  public String addNewDepartment(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Enter an valid name");
    }
    name = name.trim();
    departmentRepository.save(DepartmentModel.builder().name(name).build());
    return "New Department added";
  }

  @Transactional
  public void updateDepartment(DepartmentModel departmentModel) {
    DepartmentModel dept = departmentRepository.findById(departmentModel.getId())
        .orElseThrow(() -> new IllegalArgumentException("The department Not found"));
    dept.setName(departmentModel.getName());
  }

  @Transactional
  public void deleteDepartment(int id) {
    DepartmentModel dept = departmentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("The department Not found"));
    departmentRepository.deleteById(id);
  }

}
