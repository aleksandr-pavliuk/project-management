package ua.org.pma.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.org.pma.validator.UniqueValue;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
  @SequenceGenerator(name = "employee_seq", allocationSize = 1)
  private long employeeId;

  @NotBlank(message = "*Must give a first name")
  @Size(min = 2, max = 50)
  private String firstName;

  @NotBlank(message = "*Must give a last name")
  @Size(min = 1, max = 50)
  private String lastName;

  @NotBlank
  @Email(message = "*Must be a valid email address")
  @UniqueValue
  private String email;


  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
      CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  @JoinTable(name = "project_employee",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "project_id")
  )
  @JsonIgnore
  private List<Project> projects;

  public Employee(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
}
