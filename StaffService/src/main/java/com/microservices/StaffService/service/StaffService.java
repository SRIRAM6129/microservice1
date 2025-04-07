package com.microservices.StaffService.service;

import java.util.List;
import java.util.stream.Collectors;

import com.microservices.StaffService.client.ClassClient;
import com.microservices.StaffService.client.DepartmentClient;
import com.microservices.StaffService.client.StudentClient;
import com.microservices.StaffService.dto.StaffDTO;
import com.microservices.StaffService.exception.StaffException;
import com.microservices.StaffService.model.StaffModel;
import com.microservices.StaffService.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StaffService {

  // DEPENDENCY INJECTIONS
  @Autowired
  private StaffRepository staffRepository;
  @Autowired
  private DepartmentClient departmentClient;
  @Autowired
  private StudentClient studentClient;
  @Autowired
  private ClassClient classClient;

  // DEFAULT CRUD OPERATION

  public List<StaffModel> getAll() {
    return staffRepository.findAll();
  }

  public StaffModel getById(Integer id) throws StaffException {
    return staffRepository.findById(id).orElseThrow(() -> new StaffException("No Staff found"));
  }

  @Transactional
  public void addStaff(StaffModel staffModel) throws StaffException {
    StaffModel newStaff = staffRepository.save(staffModel);
    if (newStaff == null) {
      throw new StaffException("Not found");
    }
  }

  @Transactional
  public void updateStaff(StaffModel staffModel) throws StaffException {
    StaffModel newStaff = staffRepository.save(staffModel);
    if (newStaff == null) {
      throw new StaffException("Not found");
    }
    newStaff.setName(staffModel.getName());
    newStaff.setDeptId(staffModel.getDeptId());
    newStaff.setClassesId(staffModel.getClassesId());
    newStaff.setPhoneNumber(staffModel.getPhoneNumber());
  }

  @Transactional
  public void deleteStaff(Integer id) throws StaffException {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("Invalid id");
    }
    StaffModel existingstaff = staffRepository.findById(id).orElseThrow(() -> new StaffException("staff not found"));
    staffRepository.delete(existingstaff);
  }


}
