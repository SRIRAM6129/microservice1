package com.mircroService.departmentService.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mircroService.departmentService.client.ClassClient;
import com.mircroService.departmentService.client.StaffClient;
import com.mircroService.departmentService.client.StudentClient;
import com.mircroService.departmentService.dto.DepartmentDropDTO;
import com.mircroService.departmentService.exception.DepartmentException;
import com.mircroService.departmentService.model.DepartmentModel;
import com.mircroService.departmentService.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

  // DEPENDENCY INJECTIONS
  @Autowired
  private DepartmentRepository departmentRepository;
  @Autowired
  private StudentClient studentClient;
  @Autowired
  private ClassClient classClient;
  @Autowired
  private StaffClient staffClient;

  // DEFAULT CRUD OPERATIONS
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

  //
  // OTHER SERVICE API CALLS
  public String getDepartmentNameById(Integer id) {
    return departmentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("The department not found")).getName();
  }

  public List<DepartmentDropDTO> getDepartmentName() {
    return departmentRepository.findAll().stream()
        .map((dept) -> DepartmentDropDTO
            .builder()
            .id(dept.getId())
            .name(dept.getName())
            .build())
        .collect(Collectors.toList());
  }

  public Integer getDepartmentId(String deptName) throws DepartmentException {
    return departmentRepository.findByName(deptName).orElseThrow(() -> new DepartmentException("Invalid id")).getId();
  }

  public List<DepartmentDropDTO> getDepartmentSearchDTO() {
    return departmentRepository
        .findAll()
        .stream()
        .map((department) -> DepartmentDropDTO
            .builder()
            .build())
        .collect(Collectors.toList());
  }
}
