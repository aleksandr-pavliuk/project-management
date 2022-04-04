package ua.org.pma.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
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
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
  @SequenceGenerator(name = "project_seq", allocationSize = 1)
  private long projectId;

  private String name;

  private String stage; // NOTSTARTED, COMPLETED, INPROGRESS

  private String description;

  @NotBlank(message = "date cannot be empty")
  private Date startDate;

  @NotBlank(message = "date cannot be empty")
  private Date endDate;

  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
      CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  @JoinTable(name = "project_employee",
      joinColumns = @JoinColumn(name = "project_id"),
      inverseJoinColumns = @JoinColumn(name = "employee_id")
  )
  @JsonIgnore
  private List<Employee> employees;

  public Project(String name, String stage, String description) {
    this.name = name;
    this.stage = stage;
    this.description = description;
  }

  public void addEmployee(Employee employee) {
    if (employees == null) {
      employees = new ArrayList<>();
    }
    employees.add(employee);
  }

}
