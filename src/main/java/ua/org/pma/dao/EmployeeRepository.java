package ua.org.pma.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.org.pma.dto.EmployeeProject;
import ua.org.pma.entity.Employee;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

  @Query(nativeQuery = true, value =
      "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount "
          + "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id "
          + "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
  public List<EmployeeProject> employeeProjects();

  public Employee findByEmail(String value);

  public Employee findByEmployeeId(Long id);

}
