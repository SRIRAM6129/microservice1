package com.microServices.StudentService.service;

import java.util.List;
import java.util.stream.Collectors;

import com.microServices.StudentService.client.ClassClient;
import com.microServices.StudentService.client.DepartmentClient;
import com.microServices.StudentService.client.StaffClient;
import com.microServices.StudentService.dto.StudentDetailsDTO;
import com.microServices.StudentService.exception.StudentException;
import com.microServices.StudentService.model.StudentModel;
import com.microServices.StudentService.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private StaffClient staffClient;

  @Autowired
  private ClassClient classClient;

  @Autowired
  private DepartmentClient departmentClient;

  // DEFAULT CRUD OPERATIONS
  public List<StudentDetailsDTO> getAllStudent() throws StudentException {
    List<StudentModel> students = studentRepository.findAll();
    if (students == null) {
      throw new StudentException("No data found");
    }
    return students
        .stream()
        .map((student) -> this.studentModelToStudentDetailsDTO(student))
        .collect(Collectors.toList());
  }

  public StudentDetailsDTO getById(Integer id) throws StudentException {
    if (id == null || id < 0) {
      throw new StudentException("Invalid ID");
    }
    StudentModel student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    return studentModelToStudentDetailsDTO(student);
  }

  @Transactional
  public void addNewStudent(StudentModel studentModel) throws StudentException {
    checkStudent(studentModel);
    StudentModel student = studentRepository.save(studentModel);
    if (student == null) {
      throw new StudentException("Student registration failed");
    }
  }

  @Transactional
  public void updateStudent(StudentModel studentModel) throws StudentException {
    checkStudent(studentModel);
    StudentModel student = studentRepository.findById(studentModel.getId())
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    student.setName(studentModel.getName());
    student.setDeptId(studentModel.getDeptId());
    student.setAddress(studentModel.getAddress());
    student.setPhoneNumber(studentModel.getPhoneNumber());
  }

  @Transactional
  public void deleteStudentById(Integer id) {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("Invalid id");
    }
    StudentModel student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    studentRepository.delete(student);
  }

  // CHECKERS
  private void checkStudent(StudentModel student) throws StudentException {
    if (student.getRegisterNumber() == null || student.getRegisterNumber() < 0) {
      throw new StudentException("Invalid registerNumber");
    }
    if (student.getName() == null || student.getName().trim().isBlank()) {
      throw new StudentException("Invalid name");
    }
    if (student.getDeptId() == null || (student.getDeptId() < 0)) {
      throw new StudentException("Invalid DeptId");
    }
    if (student.getPhoneNumber() == null || student.getPhoneNumber() < 0) {
      throw new StudentException("Invalid phoneNumber");
    }
    if (student.getAddress() == null || student.getAddress().trim().isBlank()) {
      throw new StudentException("Invalid name");
    }
  }

  // DTO MAPPERS
  private StudentDetailsDTO studentModelToStudentDetailsDTO(StudentModel student) {
    return StudentDetailsDTO
        .builder()
        .name(student.getName())
        .deptId(student.getDeptId())
        .address(student.getAddress())
        .phoneNumber(student.getPhoneNumber())
        .registerNumber(student.getRegisterNumber())
        .build();
  }

  // OTHER SERVICE CALLS
  public List<StudentDetailsDTO> getStudentByDepartmentId(Integer id) throws StudentException {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("Invalid id");
    }
    List<StudentModel> students = studentRepository.findByDeptId(id)
        .orElseThrow(() -> new StudentException("Student Not Found"));
    return students
        .stream()
        .map((student) -> this.studentModelToStudentDetailsDTO(student))
        .collect(Collectors.toList());
  }
}
