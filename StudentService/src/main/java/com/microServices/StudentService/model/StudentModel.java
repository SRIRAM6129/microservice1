package  com.microServices.StudentService.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private Long registerNumber;
  private String name;
  private String password;
  private Integer deptId;
  private Long phoneNumber;
  private String address;
}
