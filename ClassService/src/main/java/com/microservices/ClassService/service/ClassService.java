package com.microservices.ClassService.service;

import java.util.List;
import java.util.stream.Collectors;

import com.microservices.ClassService.client.DepartmentClient;
import com.microservices.ClassService.client.StaffClient;
import com.microservices.ClassService.client.StudentClient;
import com.microservices.ClassService.dto.ClassNameDTO;
import com.microservices.ClassService.dto.StaffDTO;
import com.microservices.ClassService.exception.ClassException;
import com.microservices.ClassService.model.ClassModel;
import com.microservices.ClassService.repository.ClassRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClassService {

  // DEPENDENCY INJECTIONS
  @Autowired
  private ClassRepository classRepository;
  @Autowired
  private DepartmentClient departmentClient;
  @Autowired
  private StaffClient staffClient;
  @Autowired
  private StudentClient studentClient;
  // DEFAULT CRUD OPERATION

  public List<ClassModel> getAll() {
    return classRepository.findAll();
  }

  public ClassModel getById(Integer id) throws ClassException {
    return classRepository.findById(id).orElseThrow(() -> new ClassException("No class found"));
  }

  @Transactional
  public void addClass(ClassModel classModel) throws ClassException {
    ClassModel newClass = classRepository.save(classModel);
    if (newClass == null) {
      throw new ClassException("Not found");
    }
  }

  @Transactional
  public void updateClass(ClassModel classModel) throws ClassException {
    ClassModel newClass = classRepository.save(classModel);
    if (newClass == null) {
      throw new ClassException("Not found");
    }
    newClass.setDeptId(classModel.getDeptId());
    newClass.setSection(classModel.getSection());
  }

  @Transactional
  public void deleteClass(Integer id) throws ClassException {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("Invalid id");
    }
    ClassModel existingClass = classRepository.findById(id).orElseThrow(() -> new ClassException("Class not found"));
    classRepository.delete(existingClass);
  }

}
