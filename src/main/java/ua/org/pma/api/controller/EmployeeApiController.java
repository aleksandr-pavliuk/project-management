package ua.org.pma.api.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.org.pma.dao.EmployeeRepository;
import ua.org.pma.entity.Employee;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

  @Autowired
  EmployeeRepository employeeRepository;

  @GetMapping
  public Iterable<Employee> getEmployees() {
    return employeeRepository.findAll();
  }

  @GetMapping("/{id}")
  public Employee getEmployeeById(@PathVariable("id") Long id) {
    return employeeRepository.findById(id).get();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Employee create(@RequestBody @Valid Employee employee) {
    return employeeRepository.save(employee);
  }

  @PutMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public Employee update(@RequestBody @Valid Employee employee) {
    return employeeRepository.save(employee);
  }

  @PatchMapping(path = "/{id}", consumes = "application/json")
  public Employee partialUpdate(@PathVariable("id") long id,
      @RequestBody @Valid Employee patchEmployee) {
    Employee emp = employeeRepository.findById(id).get();

    if (patchEmployee.getEmail() != null) {
      emp.setEmail(patchEmployee.getEmail());
    }
    if (patchEmployee.getFirstName() != null) {
      emp.setFirstName(patchEmployee.getFirstName());
    }
    if (patchEmployee.getLastName() != null) {
      emp.setLastName(patchEmployee.getLastName());
    }

    return employeeRepository.save(emp);

  }


  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id) {
    try {
      employeeRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {

    }
  }

  @GetMapping(params = {"page", "size"})
  @ResponseStatus(HttpStatus.OK)
  public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page,
      @RequestParam("size") int size) {
    Pageable pageAndSize = PageRequest.of(page, size);

    return employeeRepository.findAll(pageAndSize);
  }
}
