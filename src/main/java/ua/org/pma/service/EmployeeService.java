package ua.org.pma.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.pma.dao.EmployeeRepository;
import ua.org.pma.dto.EmployeeProject;
import ua.org.pma.entity.Employee;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Service
public class EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  public Employee save(Employee employee) {
    return employeeRepository.save(employee);
  }

  public Iterable<Employee> getAll() {
    return employeeRepository.findAll();
  }

  public List<EmployeeProject> employeeProjects() {
    return employeeRepository.employeeProjects();
  }

  public Employee findByEmployeeId(long theId) {
    return employeeRepository.findByEmployeeId(theId);
  }

  public void delete(Employee theEmp) {
    employeeRepository.delete(theEmp);
  }
}
