package com.microServices.StudentService.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.microServices.StudentService.client.ClassClient;
import com.microServices.StudentService.client.DepartmentClient;
import com.microServices.StudentService.client.StaffClient;
import com.microServices.StudentService.dto.ClassDropDTO;
import com.microServices.StudentService.dto.DepartmentDropDTO;
import com.microServices.StudentService.dto.StudentDTO;
import com.microServices.StudentService.dto.StudentDetailsDTO;
import com.microServices.StudentService.dto.StudentTableDTO;
import com.microServices.StudentService.exception.StudentException;
import com.microServices.StudentService.model.StudentModel;
import com.microServices.StudentService.repository.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

  Logger LOG = LoggerFactory.getLogger(StudentService.class);
  // DEPENDENCY INJECTIONS
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private DepartmentClient departmentClient;
  @Autowired
  private ClassClient classClient;
  @Autowired
  private StaffClient staffClient;

  // DEFAULT CRUD OPERATIONS
  public List<StudentModel> getAllStudent() throws StudentException {
    List<StudentModel> students = studentRepository.findAll();
    if (students == null) {
      throw new StudentException("No data found");
    }
    return students;
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
        .department(student.getDeptId().toString())
        .address(student.getAddress())
        .phoneNumber(student.getPhoneNumber())
        .registerNumber(student.getRegisterNumber())
        .build();
  }

  private StudentDetailsDTO studentModelToStudentDetailsDTO(StudentModel student, String departmentName) {
    return StudentDetailsDTO
        .builder()
        .name(student.getName())
        .department(departmentName)
        .address(student.getAddress())
        .phoneNumber(student.getPhoneNumber())
        .registerNumber(student.getRegisterNumber())
        .build();
  }

  private StudentDTO studentModelToStudentDTO(StudentModel studentModel) {
    return StudentDTO
        .builder()
        .build();
  }

  // OTHER SERVICE CALLS
  public List<StudentDetailsDTO> getStudentByDepartmentId(Integer id) throws StudentException {
    if (id == null || id < 0) {
      throw new IllegalArgumentException("Invalid id");
    }
    String departmentName = departmentClient.getDepartmentName(id).getBody();
    List<StudentModel> students = studentRepository.findByDeptId(id)
        .orElseThrow(() -> new StudentException("Student Not Found"));
    return students
        .stream()
        .filter((student) -> student.getDeptId() > 0 && student.getDeptId() == id)
        .map((student) -> this.studentModelToStudentDetailsDTO(student, departmentName))
        .collect(Collectors.toList());
  }

  public List<StudentTableDTO> getStudentsForTable(Integer batchId, Integer academicYear,
      Integer deptId, Integer classId) {
    deptId = (deptId != null && deptId != 0) ? deptId : null;
    classId = (classId != null && classId != 0) ? classId : null;

    List<StudentModel> students = studentRepository
        .findByBatchAndAcademicYearWithOptionalFilters(batchId, academicYear, deptId, classId);

    HashMap<Integer, String> departments = new HashMap<>();
    List<DepartmentDropDTO> departmentDropDTOs = departmentClient.getDepartmentName().getBody();
    for (DepartmentDropDTO departmentDropDTO : departmentDropDTOs) {
      departments.put(departmentDropDTO.getId(), departmentDropDTO.getName());
    }

    HashMap<Integer, String> Sections = new HashMap<>();
    Sections.put(1, "A");
    Sections.put(2, "B");
    Sections.put(3, "C");
    Sections.put(4, "D");
    return students.stream()
        .map(student -> StudentTableDTO.builder()
            .id(student.getId())
            .name(student.getName())
            .batchId(batchId)
            .academicYear(academicYear.toString())
            .section(Sections.get(student.getClassId()))
            .departement(departments.get(student.getDeptId()))
            .build())
        .collect(Collectors.toList());
  }

  /*
   * public List<ExampleDTO> getStudentByBatch(Integer batch) {
   * List<StudentModel> students =
   * studentRepository.findByBatchId(batch).orElseThrow(() -> new
   * IllegalAccessError(""));
   * return students.stream()
   * .map((student) ->
   * ExampleDTO.builder().name(student.getName()).batch(student.getBatchStartYear(
   * )).build())
   * .collect(Collectors.toList());
   * }
   * public List<ExampleDTO> getStudentByFilter(String className, Integer batchId,
   * String department) {
   * Integer departmentId = null;
   * if (department != null) {
   * departmentId = departmentClient.getDepartmentId(department).getBody();
   * }
   * HashMap<String, Integer> hashmap = new HashMap<>();
   * hashmap.put("A", 1);
   * hashmap.put("B", 2);
   * hashmap.put("C", 3);
   * hashmap.put("D", 4);
   * hashmap.put("E", 5);
   * Integer classId = hashmap.get(className);
   * List<StudentModel> students = studentRepository.searchStudents(departmentId,
   * classId, batchId)
   * .orElseThrow(() -> new IllegalStateException("ERROR "));
   * return students
   * .stream()
   * .map((student) -> ExampleDTO
   * .builder()
   * .name(student.getName())
   * .registerNumber(student.getRegisterNumber())
   * .section(classClient.getClassName(student.getClassId()).getBody())
   * .department(departmentClient.getDepartmentName(student.getDeptId()).getBody()
   * )
   * .Academic(student.getBatchStartYear() + " - " + student.getBatchEndYear())
   * .batch(student.getBatchId())
   * .build())
   * .collect(Collectors.toList());
   * }
   */

}
